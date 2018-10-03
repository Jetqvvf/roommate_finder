$(document).ready(function() {
	function getHeight() {
	    return $(window).height() - 100;
	}	
	// Delete row on delete button click
	$(document).on("click", ".delete", function(){
        $(this).parents("tr").remove();
		$(".add-new").removeAttr("disabled");
    });
	$('#logout').on("click", function() {
		$.ajax({
			type : 'post',
			url : 'LoginControl',
			data :JSON.stringify({
				submit : "logout"
			}),
			contentType: "application/json; charset=utf-8",
			dataType : 'json',
			
			success: function(response) {
				if (response.error == 0) {
					location.href = "homepage.jsp";
				} else {
					alert("modify error");
				} 
			},
			error: function() {
			}
		});
	});
	 $("#bootstrapTable").bootstrapTable({
	        height: getHeight(),
	        method: 'get',	 
	        striped: true,	 
	        pagination: true,	   
	        url: 'ProfileControl',	
	        dataType: 'json',
	        toolbar: '#toolbar', 
	        cache: false, 
	        sidePagination: 'server', 
	        pageNumber: 1, 
	        pageSize: 10, 
	        pageList: [10,15,20, 50, 100], 
	        sortable: true,
	        sortName: 'insert_time', 
	        sortOrder: "desc",  
	        showRefresh: true,
	        showColumns: true,
	        showExport: true, 
	        search: true,
	        showToggle:false,
	        queryParamsType:'undefined',
	        queryParams: function queryParams(params){
	            var param = {    
	                 pageNumber: params.pageNumber,    
	                 pageSize: params.pageSize,
	                 sortName: params.sortName,
	                 sortOrder: params.sortOrder
	             };    
	             return param;    
	        },
	        onLoadSuccess: function(data){    
	            console.log("load success");  
	            console.log(data); 
	        },
	        onLoadError: function(status){  
	            console.log("load failure"+status);  
	        },
	        columns: [
        	{
                title: 'ID',
                sortable: false,
                formatter: function (value, row, index) {
                    return index + 1;
                }
            },{
	            title: 'Name',
	            field: 'name',
	        },{
	            title: 'City',
	            field: 'city',
	        },{
	        	title: 'Language',
	        	field: 'language',
	        },{
	            title: 'Department',
	            field: 'department',
	        },{
	        	title: 'Track',
	            field: 'track',
	        }]
	        
	        
	    })

});

