<style>
	.table-hover tbody tr:hover > td {
	  cursor: pointer;
	}
</style>

<!-- Fixed navbar -->
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}/controller/index">Fidias DDM</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
	              <a id="drop1" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">Acciones <span class="caret"></span></a>
	              <ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
	                <li role="presentation">
	                	<a id="optEliminar" style="cursor:pointer;" data-toggle="modal" role="menuitem" tabindex="-1" data-target="#eliminarProyectoModal">Eliminar Proyecto</a>
	                </li>
	                <!--  
	                <li role="presentation" class="divider"></li>
	                <li role="presentation"><a role="menuitem" tabindex="-1" href="http://twitter.com/fat">Separated link</a></li>
	                -->
	              </ul>
	            </li>
				<li><a href="#">Preferencias</a></li>
				<li><a href="#">Ayuda</a></li>
			</ul>
			<form class="navbar-form navbar-right">
				
			</form>
			<div class="nav navbar-nav navbar-right" style="width:15%;">&nbsp;</div>
			<div class="nav navbar-nav navbar-right" style="width:10%;">&nbsp;</div>
			<div id="loading" class="nav navbar-nav navbar-right" style="color: WHITE;height:40px;margin-top:0.5%;position:relative;">
				<i class="fa fa-spinner fa-5 fa-spin"></i> &nbsp;Espere por favor ...
			</div>
		</div>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="eliminarProyectoModal" tabindex="-1" role="dialog" aria-labelledby="eliminarModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body">
        <table id="eliminarProjTable" class="table table-hover">
        	<thead>
        		<th style='text-align:center;'>Nombre Proyecto</th>
        	</thead>
        	<tbody>
        		
        	</tbody>
		</table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
      </div>
    </div>
  </div>
</div>

<div id="confirm" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      Se encuentra seguro de que desea eliminar <span></span>?
      <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-primary" id="delete">Eliminar</button>
    	<button type="button" data-dismiss="modal" class="btn">Cancelar</button>
      </div>
    </div>
  </div>
</div>

<script>
	    function getParameterByName(name) {
		    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
		    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
		        results = regex.exec(location.search);
		    
		    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
		}
		
    	$(document).ready(
    		function(){
    			
    			var loading = $("#loading");
    	        $(document).ajaxStart(function () {
    	            loading.show();
    	        });

    	        $(document).ajaxStop(function () {
    	            loading.hide();
    	        });
    			
    			var username = getParameterByName('username');
    			var uuu = '';
    			if(username !== 'undefined' && username != null && username != ""){
	    			$("#username-span-value").append(username);
	    			$("#login-info-container").show();
    			} else {
    				$("#username-span-value").append(uuu);
    			}
    			
    			$('#eliminarProyectoModal').on('show.bs.modal', function () {
    				$.ajax({ // ajax call starts
    					url: '${pageContext.request.contextPath}/controller/projects', // JQuery loads serverside.php
    		 	        dataType: 'json', // Choosing a JSON datatype
    		 	        success: function(data){
    		 	        	var tbody = $("#eliminarProjTable > tbody");
    		 	        	
    		 	        	$("#eliminarProjTable > tbody > tr").remove();
    		 	        	
    		 	        	if(data.length > 0){
								$.each(data, function(idx, elem){
									var project = elem;
									tbody.append($("<tr id=\"proj_" + project.id + "\"></tr>").append($("<td></td>").append(project.name)))
									
									return;
								});
								
								$('#eliminarProjTable tr').click(function() {
						            var rowId = $(this).attr("id");
						            
						            if(confirm("Esta seguro de que desea eliminar este projecto?")){
						            	$.ajax({
					            		  	type: "DELETE",
					            		  	url: "${pageContext.request.contextPath}/controller/projects/" + rowId,
					            			success: function(data){
					            			  
												if(data.status == 0){
													$("#eliminarProyectoModal").modal("hide");	
													searchAllProjects();
													drawTreeView();
													
					            				}
					            			  
					            				return;
					            		  	}
					            		});
						            }

						            return;
						        });
								
    		 	        	} else {
    		 	        		tbody.append($("<tr></tr>").append($("<td style=\"text-align:center;\"></td>").append("No se han encontrado resultados.")))
    		 	        	}
    		 	        	
    		 	        	return;
    		 	        }
    				});
    				
    				return;
   				});
    			
    			return;
    		}
    	);
    </script>