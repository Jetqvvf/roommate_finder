$(document).ready(function() {
	function getHeight() {
	    return $(window).height() - 100;
	}	
	// Delete row on delete button click
	$(document).on("click", ".delete", function(){
        $(this).parents("tr").remove();
		$(".add-new").removeAttr("disabled");
    });
	
	 $("#bootstrapTable").bootstrapTable({
	        height: getHeight(),
	        method: 'get',	 
	        striped: true,	 
	        pagination: true,	   
	        url: 'UserControl?type=display',	
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
                align: 'center',
                valign: 'middle',
                sortable: false,
                formatter: function (value, row, index) {
                    return index+1;
                }
            },{
	            title: 'First Name',
	            align: 'center',
	            field: 'first_name',
	            valign: 'middle',
	            sortable: true,
	        },{
	            title: 'Last Name',
	            field: 'last_name',
	            align: 'center',
	            valign: 'middle',
	            sortable: true,
	        },{
	        	title: 'Username',
	        	field: 'username',
	            align: 'center',
	            valign: 'middle',
	            sortable: false
	        },{
	            title: 'Email',
	            field: 'email',
	            align: 'center',
	            valign: 'middle',
	            sortable: false
	        },{
	            title: 'Operate',
	            formatter: function(value, row, index) {
	            	var r = '<a class="edit" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a><a class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>';
	            	return r;
	            }
	        }]
	        
	        
	    })

});

