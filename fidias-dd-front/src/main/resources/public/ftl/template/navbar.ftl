<!-- Fixed navbar -->
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
    	  <ul class="nav navbar-nav pull-right">
        	<li id="index"><a href="${contextPath}/html/index">Home</a></li>
        	<!--<li id="source"><a href="${contextPath}/html/source/upload">Carga Masiva</a></li>-->
          </ul>
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Fidias-DD</a>
        </div>
        <div id="login-info-container" class="">
		    <div class="collapse navbar-collapse navbar-right" id="myo-enavbar-collapse">
		      <p class="navbar-text"><i class="glyphicon glyphicon-user" ></i> <span id="username-span-value" class="welcome"></span> | <a href="${contextPath}/logout" class="btn btn-danger btn-xs"><i class="glyphicon glyphicon-remove-circle">&nbsp;Logout</i></a>
		    </div><!-- /.navbar-collapse -->
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
    			var username = getParameterByName('username');
    			var uuu = '${username!}';
    			if(username !== 'undefined' && username != null && username != ""){
	    			$("#username-span-value").append(username);
	    			$("#login-info-container").show();
    			} else {
    				$("#username-span-value").append(uuu);
    			}
    		
    			return;
    		}
    	);
    </script>