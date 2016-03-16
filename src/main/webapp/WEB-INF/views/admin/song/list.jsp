<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>

<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>Song Management</h2>
        <ol class="breadcrumb">
            <li><a href="<spring:url value="/admin/"/>">Home</a></li>
            <li><a href="<spring:url value="/admin/song/"/>">Song Management</a></li>
            <li class="active"><strong>List</strong></li>
        </ol>
    </div>
    <div class="col-lg-2">
        <div class="title-action">
            <a href="<c:url value='create' />" class="btn btn-primary ">Add a new Song</a>
        </div>
    </div>
</div>

<div class="wrapper wrapper-content animated fadeInRight ecommerce">

    <div class="row">
        <div class="col-lg-12">
            <div class="ibox">
                <div class="ibox-content">
                
                	<input type="text" class="form-control input-sm m-b-xs" id="filter" placeholder="Search for Song">

                    <table class="footable table table-stripped toggle-arrow-tiny" data-page-size="15" data-filter=#filter>
                        <thead>
                            <tr>

                                <th>ID</th>
                                <th data-hide="phone">Name</th>
                                <th data-hide="phone">Artist</th>
                                <th data-hide="phone">Week Views</th>
                                <th data-hide="phone">Date added</th>
                                <th data-hide="phone">Play</th>
                                <th data-hide="phone,tablet">Total Views</th>
                                <th data-hide="phone,tablet">On Homepage</th>
                                <th class="text-right">Action</th>

                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach items="${songs}" var="song">
                                <tr>
                                    <td>${song.id}</td>
                                    <td>
                                        <c:out value="${song.name}" />
                                    </td>
                                    <td>
                                        <c:out value="${song.description}" />
                                    </td>
                                    <td>
                                        <fmt:formatNumber type="number" value="${song.weekViews}" />
                                    </td>
                                    <td>
                                        <fmt:formatDate type="date" value="${song.creationTime}" />
                                    </td>
                                    <td><audio src="${song.url}" preload="none"></audio></td>
                                    <td>
                                        <fmt:formatNumber type="number" value="${song.totalViews}" />
                                    </td>
                                    <td>
                                        <c:if test="${song.onHome eq true}">
                                            <span class="label label-info">Yes</span>
                                        </c:if>
                                        <c:if test="${(song.onHome eq false)  || (song.onHome eq null)}">
                                            <span class="label label-warning">No</span>
                                        </c:if>
                                    </td>
                                    <td class="text-right">
                                        <div class="btn-group">
                                            <a href="<c:url value='details-${song.id}' />" class="btn-white btn btn-xs">View</a> <a href="<c:url value='edit-${song.id}' />" class="btn-white btn btn-xs">Edit</a> <a href="<c:url value='delete-${song.id}' />" class="btn-white btn btn-xs delete-song">Delete</a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="7">
                                    <ul class="pagination pull-right"></ul>
                                </td>
                            </tr>
                        </tfoot>
                    </table>

                </div>
            </div>
        </div>
    </div>


</div>

<script>
	$(document).ready(function() {
		$('.footable').footable();
		$('.delete-song').click(function(e) {
			e.preventDefault();
			var href = $(this).attr("href");
			swal({
				title : "Are you sure?",
				text : "You will not be able to recover this song!",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "Yes, delete it!",
				closeOnConfirm : false
			}, function() {
				$.get(href, function() {
					swal("Deleted!", "Song has been deleted.", "success");
					window.location.href = "list";
				}).fail(function() {
					swal("Error", "Song could not be deleted", "error");
				});
			});
		});
	});
</script>