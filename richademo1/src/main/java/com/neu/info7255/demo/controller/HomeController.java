/**
 * 
 */
package com.neu.info7255.demo.controller;

import org.apache.http.impl.io.SocketOutputBuffer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author richabhatia
 *
 */

@RestController
@RequestMapping(value = "/")
public class HomeController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String showHome(){
		System.out.println("In home controller");
		String doctype = "<!DOCTYPE html>";
		String html = "<html>";
		String head ="<head> <title>Info7255Demo1 </title>";
		String style ="<style>";
		String ul = "ul { list-style-type: none; margin: 0; padding: 0; overflow: hidden; background-color: #333; } li { float: left; } li a { display: block; color: white; text-align: center; padding: 14px 16px; text-decoration: none; } li a:hover:not(.active) { background-color: #111; } .active { background-color: #4CAF50; }";
		String endStyle ="</style>";
		String endHead ="</head>";
		String body ="<ul> <li><a class=" +"'active'"   +" href='#home'>Home</a></li> <li><a href='#rest'>REST APIs</a></li> <li><a href='#redis'>Redis</a></li> <li><a href='#elastic'>Elastic Search</a></li> </ul>";
		String end = "<h2> Welcome to Demo1 of INFO 7255</h2> <h3> By Richa Bhatia </h3> </body> </html>";
		
		return doctype+html+head+style+ul+endStyle+endHead+body+end;
	}

}
