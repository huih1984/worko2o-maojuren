var myJobs = new Array();
function queryallMyJobs(employerId) {
    var jsonData = new Object();
    jsonData.pageIndex = 1;
    jsonData.pageSize = 20;
    jsonData.employerId = employerId;
    $.ajax({
            type: "post",
            url: '/page/demo/job/employerjob',
            data: jsonData,
            dataType: "json",
            async: false,
            success: function (data) {
//                var data = $.parseJSON(json);
                var list = data.job_list;
                if (list != undefined) {
                    for (var i = 0; i < list.length; ++i) {
                        myJobs.push(list[i]);
//                        myJobs += '<option' +
//                            ' value="' +
//                            list[i].jobId +
//                            '">' +
//                            list[i].jobName +
//                            '</option>';
                    }
                }
            }
        }
    )
}