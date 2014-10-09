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
     <h2 class="sub-header">Section title</h2>
     <div class="table-responsive">
       <table class="table table-striped">
         <thead>
           <tr>
             <th>#</th>
             <th>Header</th>
             <th>Header</th>
             <th>Header</th>
             <th>Header</th>
           </tr>
         </thead>
         <tbody>
           <tr>
             <td>1,001</td>
             <td>Lorem</td>
             <td>ipsum</td>
             <td>dolor</td>
             <td>sit</td>
           </tr>
           <tr>
             <td>1,002</td>
             <td>amet</td>
             <td>consectetur</td>
             <td>adipiscing</td>
             <td>elit</td>
           </tr>
           <tr>
             <td>1,003</td>
             <td>Integer</td>
             <td>nec</td>
             <td>odio</td>
             <td>Praesent</td>
           </tr>
           <tr>
             <td>1,003</td>
             <td>libero</td>
             <td>Sed</td>
             <td>cursus</td>
             <td>ante</td>
           </tr>
           <tr>
             <td>1,004</td>
             <td>dapibus</td>
             <td>diam</td>
             <td>Sed</td>
             <td>nisi</td>
           </tr>
           <tr>
             <td>1,005</td>
             <td>Nulla</td>
             <td>quis</td>
             <td>sem</td>
             <td>at</td>
           </tr>
           <tr>
             <td>1,006</td>
             <td>nibh</td>
             <td>elementum</td>
             <td>imperdiet</td>
             <td>Duis</td>
           </tr>
           <tr>
             <td>1,007</td>
             <td>sagittis</td>
             <td>ipsum</td>
             <td>Praesent</td>
             <td>mauris</td>
           </tr>
           <tr>
             <td>1,008</td>
             <td>Fusce</td>
             <td>nec</td>
             <td>tellus</td>
             <td>sed</td>
           </tr>
           <tr>
             <td>1,009</td>
             <td>augue</td>
             <td>semper</td>
             <td>porta</td>
             <td>Mauris</td>
           </tr>
           <tr>
             <td>1,010</td>
             <td>massa</td>
             <td>Vestibulum</td>
             <td>lacinia</td>
             <td>arcu</td>
           </tr>
           <tr>
             <td>1,011</td>
             <td>eget</td>
             <td>nulla</td>
             <td>Class</td>
             <td>aptent</td>
           </tr>
           <tr>
             <td>1,012</td>
             <td>taciti</td>
             <td>sociosqu</td>
             <td>ad</td>
             <td>litora</td>
           </tr>
           <tr>
             <td>1,013</td>
             <td>torquent</td>
             <td>per</td>
             <td>conubia</td>
             <td>nostra</td>
           </tr>
           <tr>
             <td>1,014</td>
             <td>per</td>
             <td>inceptos</td>
             <td>himenaeos</td>
             <td>Curabitur</td>
           </tr>
           <tr>
             <td>1,015</td>
             <td>sodales</td>
             <td>ligula</td>
             <td>in</td>
             <td>libero</td>
           </tr>
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
	});
 
 
 
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