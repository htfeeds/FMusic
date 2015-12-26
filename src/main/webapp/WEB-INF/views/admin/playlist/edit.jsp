<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>

<style>
    .error {
        color: #cc5965;
        display: inline-block;
        padding-left: 5px;
    }
    .success {
    	padding-top: 7px;
    	margin-bottom: 0;
    	color: #1ab394;
    }
    .failed {
    	padding-top: 7px;
    	margin-bottom: 0;
    	color: #ed5565;
    }
</style>

<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-8">
        <h2>Edit Playlist details</h2>
        <ol class="breadcrumb">
            <li><a href="<spring:url value="/admin/"/>">Home</a></li>
            <li><a href="<spring:url value="/admin/playlist/"/>">Playlist Management</a></li>
            <li class="active"><strong>Playlist edit</strong></li>
        </ol>
    </div>
    <div class="col-lg-4">
        <div class="title-action">
            <a href="<spring:url value="list "/>" class="btn btn-white">Go Back</a>
            <a href="<spring:url value="details-${playlist.id}"/>" class="btn btn-info">View Details</a>
            <a href="<spring:url value="delete-${playlist.id}"/>" class="btn btn-danger delete-playlist">Delete</a>
        </div>
    </div>
</div>

<div class="wrapper wrapper-content animated fadeInRight ecommerce">

    <div class="row">
        <div class="col-lg-12">
            <div class="tabs-container">
                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#tab-1">Playlist Info</a></li>
                    <li><a data-toggle="tab" href="#tab-2">Songs mapping</a></li>
                </ul>

                <div class="tab-content">

                    <div id="tab-1" class="tab-pane active">
                        <div class="panel-body">

                            <form:form id="editPlaylistForm" method="POST" modelAttribute="playlist" class="form-horizontal">
                                <form:hidden path="id" />
								<form:hidden path="imageUrl" />
			                    
                                <fieldset class="form-horizontal">
                                	<div class="form-group">
                                		<label class="col-lg-8">
                                			<c:if test="${success != null}">
                                				<p class="success">
                                					<c:out value="${success}" />
                                				</p>
                                			</c:if>
                                			<c:if test="${failed != null}">
                                				<p class="failed">
                                					<c:out value="${failed}" />
                                				</p>
                                			</c:if>
                                		</label>
                                    	<div class="col-lg-4" style="text-align: right">
                                       		<input type="submit" name="save" class="btn btn-primary" value="Save and Continue" />
                							<input type="submit" name="save" class="btn btn-success" value="Save" />
                                    	</div>
                                	</div>
                                
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Name</label>
                                        <div class="col-sm-10">
                                            <form:input type="text" path="name" class="form-control" placeholder="Playlist name" />
                                            <form:errors path="name" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Total Views</label>
                                        <div class="col-sm-10">
                                            <form:input type="number" path="totalViews" class="form-control" placeholder="Total Views" />
                                            <form:errors path="totalViews" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Artist</label>
                                        <div class="col-lg-4">
                                            <form:select path="artist" class="form-control">
                                            	<form:option label="Select Artist" value="" />
                                            	<form:options items="${artists}" itemLabel="name" itemValue="id" />
                                            </form:select>
                                            <form:errors path="artist" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Genre</label>
                                        <div class="col-lg-4">
                                            <form:select path="genre" class="form-control">
                                            	<form:option label="Select Genre" value="" />
                                            	<form:options items="${genres}" itemValue="id" itemLabel="name" />
                                            </form:select>
                                            <form:errors path="genre" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="hr-line-dashed"></div>
                                    
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Playlist Type</label>
                                        <div class="col-lg-4">
                                            <form:select path="playlistType" class="form-control">
                                            	<form:option label="Select PlaylistType" value="" />
                                            	<form:options items="${plTypes}" itemValue="id" itemLabel="name" />
                                            </form:select>
                                            <form:errors path="playlistType" cssClass="error" />
                                        </div>
                                    </div>
                                </fieldset>
                                
                            </form:form>

                        </div>
                    </div>
                    
                    <div id="tab-2" class="tab-pane">
                        <div class="panel-body">
                        
                            <div class="table-responsive m-b">
							    <table id="mappingArtistTable" class="table table-striped table-bordered">
							        <thead>
							            <tr>
							                <th>Song Id</th>
							                <th>Song Name</th>
							                <th>Artists</th>
							                <th>Order</th>
							                <th>Action</th>
							            </tr>
							        </thead>
							        <tbody>
							        	<c:forEach items="${songPlaylists}" var="songPlaylist">
								            <tr>
								                <td>${songPlaylist.song.id}</td>
								                <td>${songPlaylist.song.name}</td>
							                	<td>${songPlaylist.song.artists}</td>
								                <td>${songPlaylist.order}</td>
								                <td><a><i class="fa fa-times text-navy"></i></a></td>
								            </tr>
                                        </c:forEach>
							        </tbody>
							    </table>
							</div>
							
							<div class="title-action m-b">
                            	<a onclick="fnClickAddSongs();" href="javascript:void(0);" class="btn btn-primary ">Add new song</a>
                           	</div>
                        	<div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" data-page-size="15">
                                    <thead>
	                                    <tr>
	                                    	<th></th>
	                                        <th>Song Id</th>
	                                        <th>Name</th>
	                                        <th>Artists</th>
	                                        <th>Total Views</th>
	                                        <th>Action</th>
	                                    </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="song" items="${songs}">
                                    		<tr>
                                    			<td><input type="checkbox" class="i-checks songs" name="songs[]" value="${song.id}"></td>
                                    			<td>${song.id}</td>
                                    			<td>${song.name}</td>
                                    			<td>
                                    				<c:forEach items="${song.artists}" var="artist" varStatus="loop">
                                                		<c:out value="${artist.name}" />
                                                		<c:if test="${loop.index == 0 && loop.index lt fn:length(song.artists) - 1}">
                                                			<c:out value="ft."/>
                                                		</c:if>
                                                		<c:if test="${loop.index > 0 && loop.index lt fn:length(song.artists) - 1}">
                                                			<c:out value="&"/>
                                                		</c:if>
                                            		</c:forEach>
												</td>
                                    			<td>${song.totalViews}</td>
                                    			<td><a><i class="fa fa-play text-navy"></i></a></td>	
                                    		</tr>
                                    	</c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            
                        </div>
                    </div>

                </div>

            </div>
        </div>
    </div>

</div>

<script>
    $(document).ready(function() {
    	$('.delete-playlist').click(function(e) {
    	    e.preventDefault();
    	    var href = $(this).attr("href");
    	    swal({
    	            title: "Are you sure?",
    	            text: "You will not be able to recover this playlist!",
    	            type: "warning",
    	            showCancelButton: true,
    	            confirmButtonColor: "#DD6B55",
    	            confirmButtonText: "Yes, delete it!",
    	            closeOnConfirm: false
    	        },
    	        function() {
                	$.get(href,function(){
                    	swal("Deleted!", "Playlist has been deleted.", "success");
                    	window.location.href = "list";
                	}).fail(function(){
                		swal("Error", "Playlist could not be deleted", "error");
                	});
                });
    	});

        $("#editPlaylistForm").validate({
            rules: {
                name: {
                    required: true
                },
                totalViews: {
                    required: false,
                    number: true
                }
            }
        });
        
    });
    
    function fnClickAddSongs() {
    	var songs = [];
    	$('.songs:checked').each(function(i, e) {
    	    songs.push($(this).val());
    	});
    	
    	$.post('add-songs-${playlist.id}', {
            songs: songs.join()
        },
        function(response) {
            if (response != null) {
            	alert(JSON.stringify(response));
            } else {
                alert('Failure! An error has occurred!');
            }
        });
    	
    }
</script>