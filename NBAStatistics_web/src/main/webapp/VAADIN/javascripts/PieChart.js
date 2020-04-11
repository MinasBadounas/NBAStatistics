src = "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.js"

let mychart = document.getElementById('piechart').getContext('2d');

let massPopChart = new Chart(mychart, {

	type : 'pie',
	data : {
		labels : [ 'Corn Flower Blue', 'Lightgreen', 'Orange' ],
		datasets : [ {
			// label: 'Population',
			data : [ 200, 50, 40 ],
			backgroundColor : [ 'rgba(255, 99, 132, 0.6)',
					'rgba(54, 162, 235, 0.6)', 'rgba(255, 206, 86, 0.6)',
					'rgba(75, 192, 192, 0.6)', 'rgba(153, 102, 255, 0.6)',
					'rgba(255, 159, 64, 0.6)', 'rgba(255, 99, 132, 0.6)', ],
			borderWidth : 1,
			borderColor : '#777',
			hoverBorderWidth : 3,
			hoverBorderColor : '#000'
		} ]
	},

	options : {
		title : {
			display : true,
			text : 'Largest Cities In Massachusetts',
			fontSize : 25
		},
		legend : {
			display : true,
			position : 'top',
			labels : {
				fontColor : '#000'
			}
		},
		layout : {
			padding : {
				left : 50,
				right : 0,
				bottom : 0,
				top : 0
			}
		},
		tooltips : {
			enabled : true
		}
	}
});
