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


   <div class="col-md-10 col-md-offset-2 main">
	     	<div class="panel panel-default">
		     	<div class="panel-heading">
		            <a id="aInfoUpload" href="#"><i class="fa fa-info-circle fa-3 pull-right"></i></a>
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
			 <div id="tableFiles" class="table-responsive">
			    <table id="uploaded-files" class="table table-striped">
			        <tr>
			            <th>File Name</th>
			            <th>File Size</th>
			            <th>File Type</th>
			            <!-- <th>Download</th> -->
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
		        <div id="infoUpload" class="panel panel-default" style="position:absolute;top:4%;left:35%;z-index:10000;">
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
	    </div>   
	         
	
     <h2 class="sub-header">Search's Results</h2>
     <div class="table-responsive">
       <table id="projects-table-result" class="table table-bordered">
         <thead>
           <tr class="active">
             <th style="text-align:center;vertical-align:middle;">Project-Name</th>
             <th style="text-align:center;vertical-align:middle;">Table-Name</th>
             <th style="text-align:center;vertical-align:middle;">Column-Name</th>
             <th style="text-align:center;vertical-align:middle;">Type</th>
             <th style="text-align:center;vertical-align:middle;">Size</th>
             <th style="text-align:center;vertical-align:middle;">Unique</th>
             <th style="text-align:center;vertical-align:middle;">Null</th>
             <th style="text-align:center;vertical-align:middle;">Pk</th>
             <th style="text-align:center;vertical-align:middle;">Fk</th>
             <th style="text-align:center;vertical-align:middle;">Description</th>
           </tr>
         </thead>
         <tbody>
         </tbody>
       </table>
     </div>
   </div>
 <script>
 $(function () {
	 $("#tableFiles").hide();
	 $("#infoUpload").hide();
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
	                        .append($('<td/>').html(""/*"<a href='${pageContext.request.contextPath}/controller/get/"+index+"'>Click</a>"*/))
	                        )//end $("#uploaded-files").append()
	            });
	            
	            searchAllProjects();
	            drawTreeView();
	            $("#tableFiles").show();
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
	    
	    
	    
	    $("#aInfoUpload").click(
	    	function(){
	    		if(clicked){
	    			clicked = false;
	    			$("#infoUpload").hide();
	    		} else {
		    		clicked = true;
		    		$("#infoUpload").show();
	    			
	    		}
	    		
	    		return;
	    	}		
	    )/*.mouseout(
	    	function(){
	    		$("#infoUpload").hide();
	    		
	    		return;
	    	}		
	    )*/;
	    
	    return;
	});
 
 var clicked = false;
 
 	createCheckedIcon = function(input, centered){
 		var icon = "glyphicon glyphicon-unchecked";
		if(input){
			icon = "glyphicon glyphicon-check";
		}
		var style = "";
		if(centered){
			style = "margin: 0 auto; width:25%;";
		}
		
		return $("<div style='" + style + "'><i class='" + icon + "'></i></div>");
 	}
 
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
 	        			
 	        			
 	        			$.each(project.tables, function(idx, elem){
 	        				
 	        				var table = elem;
 	        				
	 	        			$.each(table.columns, function(idx, elem){
	 	        				
	 	        				var column = elem;
	 	        				
	 	        				var td1 = $("<td></td>").append(project.name);
		 	        			var td2 = $("<td></td>").append(table.name);
		 	        			var td3 = $("<td></td>").append(column.name);
		 	        			var td4 = $("<td></td>").append(column.type);
		 	        			var td5 = $("<td></td>").append(column.size);
		 	        			var td6 = $("<td class='col-centered'></td>").append(createCheckedIcon(column.unique, true));
		 	        			var td7 = $("<td class='col-centered'></td>").append(createCheckedIcon(column.nullable, true));
		 	        			var td8 = $("<td class='col-centered'></td>").append(createCheckedIcon(column.pk, false));
		 	        			var td9 = $("<td class='col-centered'></td>").append(createCheckedIcon(column.fk, false));
		 	        			var td10 = $("<td></td>").append(column.description);
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