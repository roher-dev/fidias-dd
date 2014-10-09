<style>
	#dropzone {
    background: #ccccc;
    width: 150px;
    height: 50px;
    line-height: 50px;
    text-align: center;
    font-weight: bold;
}
#dropzone.in {
    width: 600px;
    height: 200px;
    line-height: 200px;
    font-size: larger;
}
#dropzone.hover {
    background: lawngreen;
}
#dropzone.fade {
    -webkit-transition: all 0.3s ease-out;
    -moz-transition: all 0.3s ease-out;
    -ms-transition: all 0.3s ease-out;
    -o-transition: all 0.3s ease-out;
    transition: all 0.3s ease-out;
    opacity: 1;
}
</style>


   <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	     	<div class="panel panel-default">
		     	<div class="panel-heading">
		            <h3 class="panel-title">Upload File</h3>
		        </div>
		        <div class="panel-body">
		        <span class="btn btn-success fileinput-button">
				        <i class="glyphicon glyphicon-plus"></i>
				        <span>Select files...</span>
				        <input id="fileupload" type="file" name="files[]" data-url="${pageContext.request.contextPath}/controller/upload2" multiple>
				    </span>
		        <!-- <input id="fileupload" type="file" name="files[]" data-url="${pageContext.request.contextPath}/controller/upload2" multiple> -->
 
			    <div id="dropzone">Drop files here</div>
			 <!-- 
			    <div id="progress">
			        <div style="width: 0%;"></div>
			    </div>
			     -->
			    <div id="progress" class="progress">
				        <div class="progress-bar progress-bar-success"></div>
				    </div>
			 <div class="table-responsive">
			    <table id="uploaded-files" class="table table-striped">
			        <tr>
			            <th>File Name</th>
			            <th>File Size</th>
			            <th>File Type</th>
			            <th>Download</th>
			        </tr>
			    </table>
		     </div>
		        
		        <!--
			        <form action="${pageContext.request.contextPath}/upload.html" method="POST" enctype="multipart/form-data">

				    <span class="btn btn-success fileinput-button">
				        <i class="glyphicon glyphicon-plus"></i>
				        <span>Select files...</span>
				        <input id="fileupload" type="file" name="files[]" multiple>
				    </span>
				    <br>
				    <br>
				    <div id="progress" class="progress">
				        <div class="progress-bar progress-bar-success"></div>
				    </div>
				    <div id="files" class="files"></div>
				    <br>
					<input type="submit" value="Upload" class="btn btn-default">		        
		         </form>
		        
		        -->
		        
				 
		        </div>
	    </div>   
	         
	<div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">Notes</h3>
        </div>
        <div class="panel-body">
            <ul>
                <li>The maximum file size for uploads in this demo is <strong>5 MB</strong> (default file size is unlimited).</li>
                <li>Only extensible markup files (<strong>XML</strong>) are allowed in this application.</li>
                <li>You can <strong>drag &amp; drop</strong> files from your desktop on this webpage (see <a href="https://github.com/blueimp/jQuery-File-Upload/wiki/Browser-support">Browser support</a>).</li>
            </ul>
        </div>
    </div>
     <h2 class="sub-header">Resultado de la b&uacute;squeda</h2>
     <div class="table-responsive">
       <table id="projects-table-result" class="table table-bordered">
         <thead>
           <tr class="danger">
             <th>Project-Name</th>
             <th>Table-Name</th>
             <th>Column-Name</th>
             <th>Type</th>
             <th>Size</th>
             <th>Unique</th>
             <th>Null</th>
             <th>Pk</th>
             <th>Fk</th>
             <th>Description</th>
           </tr>
         </thead>
         <tbody>
         </tbody>
       </table>
     </div>
   </div>
 <script>
 $(function () {
	    $('#fileupload').fileupload({
	        dataType: 'json',
	 
	        done: function (e, data) {
	            $("tr:has(td)").remove();
	            $.each(data.result, function (index, file) {
	 
	                $("#uploaded-files").append(
	                        $('<tr/>')
	                        .append($('<td/>').text(file.fileName))
	                        .append($('<td/>').text(file.fileSize))
	                        .append($('<td/>').text(file.fileType))
	                        .append($('<td/>').html("<a href='${pageContext.request.contextPath}/controller/get/"+index+"'>Click</a>"))
	                        )//end $("#uploaded-files").append()
	            }); 
	        },
	 
	        progressall: function (e, data) {
	             var progress = parseInt(data.loaded / data.total * 100, 10);
	             $('#progress .progress-bar').css(
	                 'width',
	                 progress + '%'
	             );
	         },
	 
	        dropZone: $('#dropzone')
	    });
	    
	    searchAllProjects();
	    
	    return;
	});
 
 	searchAllProjects = function(){
 		$.ajax({ // ajax call starts
			url: '${pageContext.request.contextPath}/controller/projects', // JQuery loads serverside.php
 	        dataType: 'json', // Choosing a JSON datatype
 	        success: function(data){
 	        	var tr = $("#projects-table-result > tbody > tr");
				if(tr.size() > 0){
					
					tr.remove();
				}
 	        	var tbody = $("#projects-table-result > tbody");
 	        	
 	        	if(data.length > 0){
 	        		$.each(data, function(idx, elem){
						var counter = 0;
 	        			var project = elem;
 	        			
 	        			var td1 = $("<td></td>").append(project.name);
 	        			
 	        			$.each(project.tables, function(idx, elem){
 	        				
 	        				var table = elem;
	 	        			var td2 = $("<td></td>").append(table.name);
 	        				
	 	        			$.each(table.columns, function(idx, elem){
	 	        				
	 	        				var column = elem;
	 	        				
		 	        			var td3 = $("<td></td>").append(column.name);
		 	        			var td4 = $("<td></td>").append(column.type);
		 	        			var td5 = $("<td></td>").append(column.size);
		 	        			var td6 = $("<td></td>").append(column.unique);
		 	        			var td7 = $("<td></td>").append(column.nullable);
		 	        			var td8 = $("<td></td>").append(column.pk);
		 	        			var td9 = $("<td></td>").append(column.fk);
		 	        			var td10 = $("<td></td>").append(column.description);
								//console.log(td1.html());
								var clazz = ""
								if(counter%2 != 0){
									clazz = "success";
								}
		 	        			var tr = $("<tr class=\"" + clazz + "\"></tr>").append(td1).append(td2).append(td3).append(td4).append(td5).append(td6).append(td7).append(td8).append(td9).append(td10);
								tbody.append(tr); 	        			
		 	        			counter++;
	 	        				return;
	 	        			})
 	        				return;
 	        			})
 	        			return;
 	        		});
 	        	}
				
 	            return;
 	        }
 	    });
 		
 		return;
 	}
 
 
 
 </script>
 <script>
 /*jslint unparam: true */
 /*global window, $ */
 /*
 $(function () {
     'use strict';
     // Change this to the location of your server-side upload handler:
     var url = '${pageContext.request.contextPath}/upload.html';
     $('#fileupload').fileupload({
         url: url,
         autoUpload: true,
         dataType: 'json',
         done: function (e, data) {
             $.each(data.result.files, function (index, file) {
                 $('<p/>').text(file.name).appendTo('#files');
             });
         },
         progressall: function (e, data) {
             var progress = parseInt(data.loaded / data.total * 100, 10);
             $('#progress .progress-bar').css(
                 'width',
                 progress + '%'
             );
         }
     }).on('fileuploadadd', function (e, data) {
        data.context = $('<div/>').appendTo('#files');
        $.each(data.files, function (index, file) {
            var node = $('<p/>')
                    .append($('<span/>').text(file.name));
            node.appendTo(data.context);
        });
    }).on('fileuploadfail', function (e, data) {
        $.each(data.files, function (index) {
            var error = $('<span class="text-danger"/>').text('File upload failed. Error: ' + data.errorThrown);
            console.log("data.context.children()[index]" + data.context.children()[index]);
            $(data.context.children()[index])
                .append('<br>')
                .append(error);
        });
    }).prop('disabled', !$.support.fileInput)
         .parent().addClass($.support.fileInput ? undefined : 'disabled');
 });
 */
 </script>