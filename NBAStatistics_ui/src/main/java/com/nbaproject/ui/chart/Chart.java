package com.nbaproject.ui.chart;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nbaproject.service.team.TeamService;

@Component
public class Chart {

	@Autowired
	TeamService teamService;

	public String PieChartHtml(int teamid) {

		String html = "	<div class=\"container_chart\">\r\n"
				+ "		<canvas id=\"piechart"+teamid+"\" width='240px' height='190px'></canvas>\r\n" + "	</div>\r\n";

		return html;
	}
	
	public String BarChartHtml(int teamid) {

		String html = "	<div class=\"container_chart\">\r\n"
				+ "		<canvas id=\"barchart"+teamid+"\" width='240px' height='190px'></canvas>\r\n" + "	</div>\r\n";

		return html;
	}
	

	public String PieChartScript(Map<String, Integer> mapTeamList, int teamid, String title) {

		ArrayList<String> backgroundcolorList = new ArrayList<String>();
		backgroundcolorList.add("#375e7d");
		backgroundcolorList.add("#7c878f");
		backgroundcolorList.add("#cf1020");
		backgroundcolorList.add("#f0ce0e");

		StringBuilder stringBuilderLabels = new StringBuilder();
		StringBuilder stringBuilderData = new StringBuilder();
		StringBuilder stringBuilderbackgroundcolor = new StringBuilder();

		for (int i = 0; i < backgroundcolorList.size(); i++) {
			stringBuilderbackgroundcolor.append("\""+backgroundcolorList.get(i) +"\""+ ",");
		}

		mapTeamList.forEach((k, v) -> {
			stringBuilderLabels.append("\'" + k + "\',");
			stringBuilderData.append(v + ",");
		});

		String stringLabels = stringBuilderLabels.substring(0, stringBuilderLabels.length() - 1);
		String stringData = stringBuilderData.substring(0, stringBuilderData.length() - 1);
		String stringBackgroundColor = stringBuilderbackgroundcolor.substring(0,
				stringBuilderbackgroundcolor.length() - 1);

		String scriptJavascript = 
				"let mychart = document.getElementById('piechart"+teamid+"').getContext('2d');" + 
				"" + 
				"let massPopChart = new Chart(mychart, {" + 
				"" + 
				"type: 'pie'," + 
				"data:" + 
				"{" + 
				"labels: ["+stringLabels+"]," + 
				"datasets: [{" + 
				"data: ["+stringData+"]," + 
				"backgroundColor: [" + 
				""+stringBackgroundColor+"" + 
				"]," + 
				"borderWidth: 1," + 
				"borderColor: '#777'," + 
				"hoverBorderWidth: 3," + 
				"hoverBorderColor: '#000'" + 
				"}]" + 
				"}," + 
				"" + 
				"options:{" + 
				"title:{" + 
				"display:true," + 
				"text:'"+title + "',"+
				"fontSize:25" + 
				"}," + 
				"legend:{" + 
				"display:true," + 
				"position:'top'," + 
				"labels:{" + 
				"fontColor:'#000'" + 
				"}" + 
				"}," + 
				"layout:{" + 
				"padding:{" + 
				"left:0," + 
				"right:0," + 
				"bottom:0," + 
				"top:0" + 
				"}" + 
				"}," + 
				"tooltips:{" + 
				"enabled:true" + 
				"}" + 
				"}" + 
				"});";

	
		return scriptJavascript;

	}
	
	public String BarChartScript(Map<String, Integer> mapTeamList, int teamid, String title) {

		ArrayList<String> backgroundcolorList = new ArrayList<String>();
		backgroundcolorList.add("#375e7d");
		backgroundcolorList.add("#7c878f");
		backgroundcolorList.add("#cf1020");
		backgroundcolorList.add("#f0ce0e");


		StringBuilder stringBuilderLabels = new StringBuilder();
		StringBuilder stringBuilderData = new StringBuilder();
		StringBuilder stringBuilderbackgroundcolor = new StringBuilder();

		for (int i = 0; i < backgroundcolorList.size(); i++) {
			stringBuilderbackgroundcolor.append("\""+backgroundcolorList.get(i) +"\""+ ",");	
		}

		mapTeamList.forEach((k, v) -> {
			stringBuilderLabels.append("\'" + k + "\',");
			stringBuilderData.append(v + ",");
		});

		String stringLabels = stringBuilderLabels.substring(0, stringBuilderLabels.length() - 1);
		String stringData = stringBuilderData.substring(0, stringBuilderData.length() - 1);
		String stringBackgroundColor = stringBuilderbackgroundcolor.substring(0,
				stringBuilderbackgroundcolor.length() - 1);

		String scriptJavascript = 
				"let mychart = document.getElementById('barchart"+teamid+"').getContext('2d');" + 
				"" + 
				"let massPopChart = new Chart(mychart, {" + 
				"" + 
				"type: 'bar'," + 
				"data:" + 
				"{" + 
				"labels: ["+stringLabels+"]," + 
				"datasets: [{" + 
				"data: ["+stringData+"]," + 
				"backgroundColor: [" + 
				""+stringBackgroundColor+"" + 
				"]," + 
				"borderWidth: 1," + 
				"borderColor: '#777'," + 
				"hoverBorderWidth: 3," + 
				"hoverBorderColor: '#000'" + 
				"}]" + 
				"}," + 
				"" + 
				"options:{" + 
				"title:{" + 
				"display:true," + 
				"text:'"+ title + "',"+
				"fontSize:25" + 
				"}," + 
				"legend:{" + 
				"display:false," + 
				"position:'top'," + 
				"labels:{" + 
				"fontColor:'#000'" + 
				"}" + 
				"}," + 
				"layout:{" + 
				"padding:{" + 
				"left:0," + 
				"right:0," + 
				"bottom:0," + 
				"top:0" + 
				"}" + 
				"}," + 
				"tooltips:{" + 
				"enabled:true" + 
				"}," + 
				"scales: {" + 
				"yAxes: [{" + 
				"ticks: {" + 
				"beginAtZero: true" + 
				"}" + 
				"}]" + 
				"}"+
				"}" + 
				"});";

		
		return scriptJavascript;

	}
	
}
