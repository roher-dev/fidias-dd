<!-- Fixed navbar -->
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${contextPath}/html/index">Fidias DD</a>
    </div>
    <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Dashboard</a></li>
        <li><a href="#">Settings</a></li>
        <li><a href="#">Profile</a></li>
        <li><a href="#">Help</a></li>
      </ul>
      <form class="navbar-form navbar-right">
        <input type="text" class="form-control" placeholder="Search...">
      </form>
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