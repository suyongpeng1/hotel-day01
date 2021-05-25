/*********************************自定义工具函数**********************************/
//将目前的时间格式2019/08/06 12:12:08  -->  2019/08/06
function getDateStr(dateStr) {
    var indexOf = dateStr.indexOf(" ");  //取到" "的下标
    dateStr = dateStr.substring(0,indexOf);  //第1个参数为下标，第2个参数为切割的字符串长度
    return dateStr;
}

//计算天数
function getDays(startDate,endDate){  //2019/09/09   2019/10/10
    var date1Str = startDate.split("/");
    var date1Obj = new Date(date1Str[0],(date1Str[1]-1),date1Str[2]);
    var date2Str = endDate.split("/");
    var date2Obj = new Date(date2Str[0],(date2Str[1]-1),date2Str[2]);
    var t1 = date1Obj.getTime();
    var t2 = date2Obj.getTime();
    var datetime=1000*60*60*24;
    var minusDays = Math.floor(((t2-t1)/datetime));
    var days = Math.abs(minusDays);
    return minusDays;
}

//获取当前时间字符串     Date()   ---->  yyyy/MM/dd HH:mm:ss 格式的字符串
function getNowDate(date) {
    var sign1 = "/";
    var sign2 = ":";
    var year = date.getFullYear() // 年
    var month = date.getMonth() + 1; // 月
    var day  = date.getDate(); // 日
    var hour = date.getHours(); // 时
    var minutes = date.getMinutes(); // 分
    var seconds = date.getSeconds() //秒
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (day >= 0 && day <= 9) {
        day = "0" + day;
    }
    if (hour >= 0 && hour <= 9) {
        hour = "0" + hour;
    }
    if (minutes >= 0 && minutes <= 9) {
        minutes = "0" + minutes;
    }
    if (seconds >= 0 && seconds <= 9) {
        seconds = "0" + seconds;
    }
    var currentdate = year + sign1 + month + sign1 + day + " " + hour + sign2 + minutes + sign2 + seconds ;
    return currentdate;
}
//把 2019/01/01 12:12:12  -->  20190101121212
function dateReplace(date) {
    date = date.replace("/","");
    date = date.replace("/","");
    date = date.replace(" ","");
    date = date.replace(":","");
    date = date.replace(":","");
    return date;
}

//获取随机数
function getRandom(num) {
    var count = '';   //随机数
    for (var i=0;i<num;i++){
        count += parseInt(Math.random()*10)  //0.123123123...
    }
    return count;
}
