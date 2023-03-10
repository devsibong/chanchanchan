$(document).ready(function() {
	function c(passed_month, passed_year, calNum) {
		var calendar = calNum == 0 ? calendars.cal1 : calendars.cal2;
		makeWeek(calendar.weekline);
		calendar.datesBody.empty();
		var calMonthArray = makeMonthArray(passed_month, passed_year);
		var r = 0;
		var u = false;
		while (!u) {
			if (daysArray[r] == calMonthArray[0].weekday) {
				u = true
			} else {
				calendar.datesBody.append('<div class="blank"></div>');
				r++;
			}
		}
		for (var cell = 0; cell < 42 - r; cell++) { // 42 date-cells in calendar
			if (cell >= calMonthArray.length) {
				calendar.datesBody.append('<div class="blank"></div>');
			} else {
				var shownDate = calMonthArray[cell].day;
				var iter_date = new Date(passed_year, passed_month, shownDate);
				var today = new Date();
				var temp = new Date(today.setDate(today.getDate()+4));
				if (
					(
						(shownDate != temp.getDate() && passed_month == temp.getMonth()) || passed_month != temp.getMonth()) && iter_date < temp) {
					var m = '<div class="past-date">';
				} else {
					var m = checkToday(iter_date) ? '<div>' : "<div>";
				}
				calendar.datesBody.append(m + shownDate + "</div>");
			}
		}

		var color = "#444444";
		calendar.calHeader.find("h2").text(passed_year + "년  " + i[passed_month] + "월");
		calendar.weekline.find("div").css("color", color);
		calendar.datesBody.find(".today").css("color", "#00bdaa");

		// find elements (dates) to be clicked on each time
		// the calendar is generated
		var clicked = false;
		selectDates(selected);

		clickedElement = calendar.datesBody.find('div');
		
		clickedElement.on("click", function() {
			clicked = $(this);
			var whichCalendar = calendar.name;
			var clickedDate = clicked.text();
			var clickedMonth = i[passed_month];
			var clickedYear = passed_year;
			var clickedDateObj = new Date(clickedYear, clickedMonth-1, clickedDate);
			var clickedDateObjVal = new Date(clickedDateObj.valueOf());
			var regDate1 = new Date((clickedDateObjVal.setDate(clickedDateObjVal.getDate()+7)).valueOf());
			var regDate2 = new Date((clickedDateObjVal.setDate(clickedDateObjVal.getDate()+7)).valueOf());
			var regDate3 = new Date((clickedDateObjVal.setDate(clickedDateObjVal.getDate()+7)).valueOf());
			if(temp < clickedDateObj) {
				clicked.parent().find(".today").removeClass("today");
				clicked.addClass("today");
				$("#regular_shippingdate").val(dateObjView(clickedDateObj) +"\n"+ dateObjView(regDate1) +"\n"+ dateObjView(regDate2) +"\n"+ dateObjView(regDate3));
			} else {
				alert("유효한 날짜가 아닙니다.");
			};
		});

	}
	
	function dateObjView(obj) {
		var year = obj.getFullYear();
		var month = i[obj.getMonth()];
		var date = obj.getDate();
			if(date < 10) {
				date = "0" + obj.getDate();
			}
		var day = obj.getDay();
		var weekday = new Array(7);
			weekday[0] = "일요일";
			weekday[1] = "월요일";
			weekday[2] = "화요일";
			weekday[3] = "수요일";
			weekday[4] = "목요일";
			weekday[5] = "금요일";
			weekday[6] = "토요일";
		var dateText = year + "년 " + month + "월 " + date + "일 " + weekday[day];
		return dateText;
	}
	function selectDates(selected) {
		if (!$.isEmptyObject(selected)) {
			var dateElements1 = datesBody1.find('div');
			var dateElements2 = datesBody2.find('div');

			function highlightDates(passed_year, passed_month, dateElements) {
				if (passed_year in selected && passed_month in selected[passed_year]) {
					var daysToCompare = selected[passed_year][passed_month];
					for (var d in daysToCompare) {
						dateElements.each(function(index) {
							if (parseInt($(this).text()) == daysToCompare[d]) {
								$(this).addClass('selected');
							}
						});
					}

				}
			}

			highlightDates(year, month, dateElements1);
			highlightDates(nextYear, nextMonth, dateElements2);
		}
	}

	function makeMonthArray(passed_month, passed_year) { // creates Array specifying dates and weekdays
		var e = [];
		for (var r = 1; r < getDaysInMonth(passed_year, passed_month) + 1; r++) {
			e.push({
				day: r,
				// Later refactor -- weekday needed only for first week
				weekday: daysArray[getWeekdayNum(passed_year, passed_month, r)]
			});
		}
		return e;
	}

	function makeWeek(week) {
		week.empty();
		for (var e = 0; e < 7; e++) {
			week.append("<div>" + daysArray[e].substring(0, 3) + "</div>")
		}
	}

	function getDaysInMonth(currentYear, currentMon) {
		return (new Date(currentYear, currentMon + 1, 0)).getDate();
	}

	function getWeekdayNum(e, t, n) {
		return (new Date(e, t, n)).getDay();
	}

	function checkToday(e) {
		var temp = new Date(today.setDate(today.getDate()+3));
		var todayDate = temp.getFullYear() + '/' + (temp.getMonth() + 1) + '/' + temp.getDate();
		var checkingDate = e.getFullYear() + '/' + (e.getMonth() + 1) + '/' + e.getDate();
		return todayDate == checkingDate;

	}

	function getAdjacentMonth(curr_month, curr_year, direction) {
		var theNextMonth;
		var theNextYear;
		if (direction == "next") {
			theNextMonth = (curr_month + 1) % 12;
			theNextYear = (curr_month == 11) ? curr_year + 1 : curr_year;
		} else {
			theNextMonth = (curr_month == 0) ? 11 : curr_month - 1;
			theNextYear = (curr_month == 0) ? curr_year - 1 : curr_year;
		}
		return [theNextMonth, theNextYear];
	}

	function b() {
		today = new Date;
		year = today.getFullYear();
		month = today.getMonth();
		var nextDates = getAdjacentMonth(month, year, "next");
		nextMonth = nextDates[0];
		nextYear = nextDates[1];
	}

	var e = 480;

	var today;
	var year,
		month,
		nextMonth,
		nextYear;

	var r = [];
	var i = [
		"01",
		"02",
		"03",
		"04",
		"05",
		"06",
		"07",
		"08",
		"09",
		"10",
		"11",
		"12"];
	var daysArray = [
		"일",
		"월",
		"화",
		"수",
		"목",
		"금",
		"토"];

	var cal1 = $("#calendar_first");
	var calHeader1 = cal1.find(".calendar_header");
	var weekline1 = cal1.find(".calendar_weekdays");
	var datesBody1 = cal1.find(".calendar_content");

	var cal2 = $("#calendar_second");
	var calHeader2 = cal2.find(".calendar_header");
	var weekline2 = cal2.find(".calendar_weekdays");
	var datesBody2 = cal2.find(".calendar_content");

	var bothCals = $(".calendar");

	var switchButton = bothCals.find(".calendar_header").find('.switch-month');

	var calendars = {
		"cal1": {
			"name": "first",
			"calHeader": calHeader1,
			"weekline": weekline1,
			"datesBody": datesBody1
		},
		"cal2": {
			"name": "second",
			"calHeader": calHeader2,
			"weekline": weekline2,
			"datesBody": datesBody2
		}
	}


	var clickedElement;
	var firstClicked,
		secondClicked,
		thirdClicked;
	var firstClick = false;
	var secondClick = false;
	var selected = {};

	b();
	c(month, year, 0);
	c(nextMonth, nextYear, 1);
	switchButton.on("click", function() {
		var clicked = $(this);
		var generateCalendars = function(e) {
			var nextDatesFirst = getAdjacentMonth(month, year, e);
			var nextDatesSecond = getAdjacentMonth(nextMonth, nextYear, e);
			month = nextDatesFirst[0];
			year = nextDatesFirst[1];
			nextMonth = nextDatesSecond[0];
			nextYear = nextDatesSecond[1];

			c(month, year, 0);
			c(nextMonth, nextYear, 1);
		};
		if (clicked.attr("class").indexOf("left") != -1) {
			generateCalendars("previous");
		} else {
			generateCalendars("next");
		}
		clickedElement = bothCals.find(".calendar_content").find("div");
	});


	//  Click picking stuff
	function getClickedInfo(element, calendar) {
		var clickedInfo = {};
		var clickedCalendar,
			clickedMonth,
			clickedYear;
		clickedCalendar = calendar.name;
		clickedMonth = clickedCalendar == "first" ? month : nextMonth;
		clickedYear = clickedCalendar == "first" ? year : nextYear;
		clickedInfo = {
			"calNum": clickedCalendar,
			"date": parseInt(element.text()),
			"month": clickedMonth,
			"year": clickedYear
		}
		return clickedInfo;
	}


});
