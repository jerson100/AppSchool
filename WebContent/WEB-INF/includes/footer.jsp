<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<div class="footer">
	<div class="footer-inner">
		<!-- #section:basics/footer -->
		<div class="footer-content">
		<%
		
			Date now = new Date();
		
			SimpleDateFormat f = new SimpleDateFormat("YYYY");
			
			String fe = f.format(now);
			
		%>
		
			<span class="bigger-120"> 
				Aula Virtual &copy; <%=fe%>
			</span> &nbsp; &nbsp; <span class="action-buttons"> <!--<a href="#">
					<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
			</a> --><a href="https://www.facebook.com/Institucion-Educativa-Privada-Uribes-School-237664802950794/" target="_blank"> <i
					class="ace-icon fa fa-facebook-square text-primary bigger-150"></i>
			</a> <!--<a href="#"> <i
					class="ace-icon fa fa-rss-square orange bigger-150"></i>
			</a>-->
			</span>
		</div>
	</div>
</div>