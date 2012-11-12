package com.hts.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.hts.entity.Channel;
import com.hts.exceptions.AppException;
import com.hts.service.ChannelServiceImpl;

public class VideoList extends HttpServlet {

	private static final long serialVersionUID = 17227839L;
	private static final String VOD_DIRECTORY = "/streams";
	private static final String FLV_MEDIA = ".flv";
	private static final String MP4_MEDIA = ".mp4";

	final Logger log = Logger.getLogger(this.getClass());
	protected ChannelServiceImpl channelService = new ChannelServiceImpl();

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		// TODO Add check for room access permission. If no access extend XML to mark channel inactive but present

		log.debug("executing VideoList.Service");

		PrintWriter out = response.getWriter();
		String fileName;

		// Channels to display in xml
		ArrayList<Channel> channels = new ArrayList<Channel>();

		// Getting media files as channels
		try {
			File vodDir = new File(getServletContext().getRealPath(VOD_DIRECTORY));

			File[] listOfFiles = vodDir.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {

				if (listOfFiles[i].isFile()) {
					fileName = listOfFiles[i].getName();
					if (fileName.endsWith(FLV_MEDIA) || fileName.endsWith(MP4_MEDIA)) {
						Channel channel = getChannelByFile(fileName);
						if (channel != null) {
							channels.add(channel);
						}
					}
				}
			}

		}
		catch (Exception e) {
			log.error(e);
		}

		// Gettoing broadcast streams as channels
		// Very cold in the office. lost of typos
		ChannelServiceImpl channelServiceImpl = new ChannelServiceImpl();

		try {
				channels.addAll(channelServiceImpl.getActive()) ;
		}
		catch (AppException e) {
			log.error(e);
		}
		try {
			out.print(arraytoXML(channels, request.getLocalAddr() + request.getContextPath()));
		}
		catch (ParserConfigurationException e) {
			log.error(e);
		}
		catch (TransformerException e) {
			log.error(e);
		}

	}

	Channel getChannelByFile(String fileName) throws AppException {
		log.debug("Getting channel for vod: " + fileName);
		return channelService.getByBroadcastName(fileName);

	}

	String arraytoXML(ArrayList<Channel> channels, String serverAdd) throws ParserConfigurationException,
			TransformerException {

		/**
		 * <rss version="2.0"> 
		 * <channel> 
		 *    <item> <title>Argentina Football Match1</title>
		 *    <link>rtmp://localhost/oflaDemo/red5StreamDemo</link> 
		 *    <description> Goals from a football match. </description> 
		 *   </item> 
		 * </channel>
		 * 
		 */

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("rss");
		rootElement.setAttribute("version", "2.0");
		doc.appendChild(rootElement);

		Element domChannel = doc.createElement("channel");
		rootElement.appendChild(domChannel);

		for (Channel ch : channels) {
			Element item = doc.createElement("item");
			domChannel.appendChild(item);

			Element title = doc.createElement("title");
			title.appendChild(doc.createTextNode(ch.getChannelName()));
			item.appendChild(title);

			Element link = doc.createElement("link");
			link.appendChild(doc.createTextNode("rtmp://" + serverAdd + "/" + ch.getBroadcastStream()));
			item.appendChild(link);

			Element description = doc.createElement("description");
			description.appendChild(doc.createTextNode(ch.getDescription()));
			item.appendChild(description);

		}

		// ///////////////
		// Output the XML
		// set up a transformer
		TransformerFactory transfac = TransformerFactory.newInstance();
		Transformer trans = transfac.newTransformer();
		trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		trans.setOutputProperty(OutputKeys.INDENT, "yes");

		// create string from xml tree
		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		DOMSource source = new DOMSource(doc);
		trans.transform(source, result);
		String xmlString = sw.toString();

		return xmlString;

	}

}
