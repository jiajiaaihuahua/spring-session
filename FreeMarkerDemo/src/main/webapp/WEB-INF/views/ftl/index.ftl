<html>
<body>
	<div id="header">
	  <h2>FreeMarker Spring MVC</h2>
	</div>
	<div id="content">
	  <fieldset>
	    <legend>list</legend>
	    <form name="save" action="save" method="post">
	      Make : <input type="text" name="name" /><br/>
	      Model: <input type="text" name="age" /><br/>
	      <input type="submit" value="Save" />
	    </form>
	  </fieldset>
	  <br/>
	  <table class="datatable">
	    <tr>
	      <th>Make</th>
	      <th>Model</th>
	    </tr>
	    <#list model["list"] as student>
	      <tr>
	        <td>${student.name}</td>
	        <td>${student.age}</td>
	      </tr>
	    </#list>
	  </table>
	</div>
</body>
</html>