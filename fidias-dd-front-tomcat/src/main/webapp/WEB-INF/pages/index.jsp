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
			<div id="errorContainer" class="alert alert-danger alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
			  <strong>Cuidado!&nbsp;</strong><span></span>
			</div>
			<div class="panel panel-default">
		     	<div class="panel-heading">
		     		<h4 class="panel-title">Filtro</h4>
		     	</div>
		     	<div class="panel-body">
		     		<div class="input-group input-group-sm">
						<input id="projectSearch" type="text" class="form-control" placeholder="Buscar...">
						<span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>
					</div>
		     	</div>
		    </div>
	     	<div class="panel panel-default">
		     	<div class="panel-heading">
		            <a id="aInfoUpload" href="#"><i class="fa fa-info-circle fa-3 pull-right"></i></a>
		            <h4 class="panel-title">Subir Archivo</h4>
		        </div>
		        <div class="panel-body">
		        <span class="btn btn-success fileinput-button">
				        <i class="glyphicon glyphicon-plus"></i>
				        <span>Seleccionar archivos...</span>
				        <input id="fileupload" type="file" name="files[]" data-url="${pageContext.request.contextPath}/controller/upload2" multiple>
				    </span>
		        <!-- <input id="fileupload" type="file" name="files[]" data-url="${pageContext.request.contextPath}/controller/upload2" multiple> -->
 
			    <div id="dropzone">Drop files here ...</div>
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
		        </div>
		        <div id="infoUpload" class="panel panel-warning" style="position:absolute;top:4%;left:35%;z-index:10000;">
			        <div class="panel-heading">
			            <h3 class="panel-title">Informaci&oacute;n</h3>
			        </div>
			        <div class="panel-body">
			            <ul>
			                <li>El tama&ntilde;o m&aacute;ximo de un archivo permitido es de <strong>5 MB</strong>.</li>
			                <li>La extensi&oacute;n de archivo permitida a subir es "extensible markup files" (<strong>XML</strong>).</li>
			                <li>Tambi&eacute;n se puede hacer mediante <strong>drag &amp; drop</strong> el upload de archivos desde su escritorio.</li>
			            </ul>
			        </div>
			    </div>
	    </div>   
	    <div class="panel panel-default">
	     	<div class="panel-heading">
	     		<h4 class="panel-title">Resultado de la b&uacute;squeda</h4>
	     	</div>
	     	<div class="panel-body">
	     		<div class="table-responsive">
			       <table id="projects-table-result" class="table table-bordered">
			         <thead>
			           <tr class="active">
			             <th style="text-align:center;vertical-align:middle;">Projecto</th>
			             <th style="text-align:center;vertical-align:middle;">Tabla</th>
			             <th style="text-align:center;vertical-align:middle;">Nombre Columna</th>
			             <th style="text-align:center;vertical-align:middle;">Tipo</th>
			             <th style="text-align:center;vertical-align:middle;">Tama&ntilde;o</th>
			             <th style="text-align:center;vertical-align:middle;">Unique</th>
			             <th style="text-align:center;vertical-align:middle;">Nullable</th>
			             <th style="text-align:center;vertical-align:middle;">Pk</th>
			             <th style="text-align:center;vertical-align:middle;">Fk</th>
			             <th style="text-align:center;vertical-align:middle;">Descripci&oacute;n</th>
			           </tr>
			         </thead>
			         <tbody>
			         </tbody>
			       </table>
     </div>
	     	</div>
	    </div>
     
   </div>
 <script>
 $(function () {
	 $("#errorContainer").hide();
	 $("#tableFiles").hide();
	 $("#infoUpload").hide();
	    $('#fileupload').fileupload({
	        dataType: 'json',
	 
	        done: function (e, data) {
	            $("tr:has(td)").remove();
	            if(data.result.status != 0){
		            $("#errorContainer > span").html(data.result.message);
		            $("#errorContainer").show();
	            } else {
	            	$("#errorContainer").hide();
	            	$("#errorContainer > span").html();
	            	
	            	var uploadDiv = $("#uploaded-files");
	            	
		            $.each(data.result.files, function (index, file) {
		 
		            	var tr = $("<tr></tr>")
		            	
		            	var td1 = $("<td></td>").append(file.fileName);
		            	var td2 = $("<td></td>").append(file.fileSize);
		            	var td3 = $("<td></td>").append(file.fileType);
		            	var td4 = $("<td></td>");
		            	
		            	tr.append(td1).append(td2).append(td3).append(td4);
		            	
		            	uploadDiv.append(tr);
		            	
		            	return;
		            });
	            }
	            
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
	    			$("#infoUpload").hide('slow');
	    		} else {
		    		clicked = true;
		    		$("#infoUpload").show('slow');
	    			
	    		}
	    		
	    		return;
	    	}		
	    )/*.mouseout(
	    	function(){
	    		$("#infoUpload").hide();
	    		
	    		return;
	    	}		
	    )*/;
	    
	    $("#projectSearch").autocomplete({
	      source: function( request, response ) {
	          $.ajax({
	            url: '${pageContext.request.contextPath}/controller/projects/filter/' + request.term,// "http://gd.geobytes.com/AutoCompleteCity",
	            data: {
	              q: request.term
	            },
	            success: function( data ) {
	              response( data.list );
	            }
	          });
	        },
	        minLength: 3,
	        select: function( event, ui ) {
	          console.log( ui.item ?
	            "Selected: " + ui.item.label :
	            "Nothing selected, input was " + this.value);
	          
	          if(ui.item != null && ui.item !== 'undefined'){
		          $.ajax({ // ajax call starts
						url: '${pageContext.request.contextPath}/controller/projects/get/' + ui.item.label,
			 	        dataType: 'json', // Choosing a JSON datatype
			 	        success: function(data){
			 	        	
			 	        	drawProjects(data);
							
			 	            return;
			 	        }
			 	    });	
	          }
	        },
	        open: function() {
	          $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
	        },
	        close: function() {
	          $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
	        }
	      });
	    
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
 	        	} else {
 	        		var tr = $("<tr class=\"\"></tr>").append("<td style='text-align:center;' colspan='10'>No se han encontrado resultados</td>")
 	        		tbody.append(tr);
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