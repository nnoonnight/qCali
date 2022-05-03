<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- fullcalendar CDN -->
<link
	href='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.css'
	rel='stylesheet' />
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.js'></script>
<!-- fullcalendar 언어 CDN -->
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/locales-all.min.js'></script>
<!-- moment -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.0/moment.min.js"
	type="text/javascript"></script>


<script>

  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      headerToolbar: {
        left: 'prev,next today',
        center: 'title'
      },
      navLinks: true, // can click day/week names to navigate views
      selectable: true,
      selectMirror: true,
//       select: function(arg) {
//     	  console.log(arg);
    	  
//         var title = prompt('입력할 일정:');
//         //tit;e값이 있을때, 화면에 calander.addEvent() json 형식으로 일정을 추가
//         if (title) {
//           calendar.addEvent({
//             title: title,
//             start: arg.start,
//             end: arg.end,
//             allDay: arg.allDay
//           })
//         }
//         calendar.unselect()
//       },
      //있는 일정 클릭시 
      eventClick: function(arg) {
    	  
//     	  console.log("#등록된 일정 클릭#")
//      	  console.log(arg.event)
// 		console.log(arg.event.start);
//     	alert("Clicked on : " + arg.event.start);
		var start = moment(arg.event.start).format('YYMMDD');
		console.log(moment(start).format('YYMMDD'));
		$.ajax({
			type : 'POST',
			url : '<c:url value="/board/listDay" />',
//			datatype:"html",
			dataType:'text',
			data : 'date='+start,
			async:false,
			success:function(data){
// 				console.log(data);
// 				alert(data);
				window.location.href='<c:url value="/board/listDay?date=" />'+data;
				//location.replace('<c:url value="/board/listDay?{}"/>');
				//window.location.href='<c:url value="board/listDay?date=start" />'
			}
		
		})
      },
      dayMaxEvents: true, // allow "more" link when too many events
      events:
    	  //Ajax 데이터 불러올 부분
	[
	<c:forEach var="list" items= "${list}">
			{
				title:+'${list.getCount()}',
				start:'${list.getCalendarDay()}'
				
			},
	</c:forEach>
	]
    });
    calendar.render();
  });
</script>

<style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
	font-size: 10px;
}

#calendar {
	max-width: 1100px;
	margin: 0 auto;
	width:350px;
}
#fc-header-toolbar fc-toolbar fc-toolbar-ltr{
	font-size: .9em;
}
</style>
</head>
<body>
	<div class='calendar-parent'>
		<div id='calendar'></div>
	</div>
</body>
</html>
