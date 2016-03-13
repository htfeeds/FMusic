<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../shared/taglib.jsp"%>

<!-- main content area -->
<main>
	<section id="events">
		<div class="container" style="padding: 0;">
			<div class="row">
				<!-- left side --> 
				<div class="col-md-8 upcoming-events">
					
					<!-- banner-slider -->
					<c:if test="${slidePlaylists != null}">
						<div id="jssor_1" style="position: relative; margin: 0 auto; top: 0px; left: 0px; width: 600px; height: 300px; overflow: hidden; visibility: hidden;">
							<!-- Loading Screen -->
							<div data-u="loading" style="position: absolute; top: 0px; left: 0px;">
								<div style="filter: alpha(opacity=70); opacity: 0.7; position: absolute; display: block; top: 0px; left: 0px; width: 100%; height: 100%;"></div>
								<div style="position:absolute;display:block;background:url('static/img/loading.gif') no-repeat center center;top:0px;left:0px;width:100%;height:100%;"></div>
							</div>
							<div data-u="slides" style="cursor: default; position: relative; top: 0px; left: 0px; width: 600px; height: 300px; overflow: hidden;">
								<c:forEach items="${slidePlaylists}" var="pl">
									<div data-p="112.50" style="display: none;">
										<a href="#"><img data-u="image" src="<c:url value="${pl.slideImageUrl}"/>" /></a>
										<img data-u="thumb" src="<c:url value="${pl.slideImageUrl}"/>" />
									</div>
								</c:forEach>
							</div>
							<!-- Thumbnail Navigator -->
							<div u="thumbnavigator" class="jssort03" style="position:absolute;left:0px;bottom:0px;width:600px;height:60px;" data-autocenter="1">
								<div style="position: absolute; top: 0; left: 0; width: 100%; height:100%; background-color: #000; filter:alpha(opacity=30.0); opacity:0.3;"></div>
								<!-- Thumbnail Item Skin Begin -->
								<div u="slides" style="cursor: default;">
									<div u="prototype" class="p">
										<div class="w">
											<div u="thumbnailtemplate" class="t"></div>
										</div>
										<div class="c"></div>
									</div>
								</div>
								<!-- Thumbnail Item Skin End -->
							</div>
						</div>
					</c:if>
					
					<!-- Lastest Albums --> 
					<c:if test="${homePlaylists != null}">
						<h2>Latest <span>Albums</span> <a href="#" class="view-all"><span>View All</span> <i class="fa fa-angle-double-right view-all-icon"></i></a></h2>
						<div class="row">
							<c:forEach items="${homePlaylists.content}" begin="0" end="16" var="pl">
								<div class="col-xs-6 col-sm-3">
									<div class="latest-content">
										<a href="#">
											<div class="latest-content-image"><img src="<c:url value="${pl.imageUrl}"/>" /></div>
											<div class="latest-content-info">
												<div class="meta">
													<div class="icon"><i class="fa fa-headphones"></i></div>
													<h4>${pl.name}</h4>
													<p>${pl.artist.name}</p>
												</div>
											</div>
										</a>
									</div>
								</div>
							</c:forEach>
						</div>
					</c:if>

					<!-- Lastest Songs -->
					<c:if test="${homeSongs != null}">
						<h2>Latest <span>Songs</span> <a href="#" class="view-all"><span>View All</span> <i class="fa fa-angle-double-right view-all-icon"></i></a></h2>
						<div class="row">
							<div class="col-sm-6">
								<ul class="songs" style="margin:0px">
									<c:forEach items="${homeSongs}" var="song" begin="0" end="20" step="2">
										<li>
											<div class="track-meta">
												<h5><a href="#" style="font-size:15px;"><c:out value="${song.name}" /></a></h5>
												<p>
													<a href="#" style="font-size:14px">
														<c:forEach items="${song.artists}" var="artist" varStatus="loop">
			                                                <c:out value="${artist.name}" />
			                                                <c:if test="${loop.index == 0 && loop.index lt fn:length(song.artists) - 1}">
			                                                	<c:out value="ft."/>
			                                                </c:if>
			                                                <c:if test="${loop.index > 0 && loop.index lt fn:length(song.artists) - 1}">
			                                                	<c:out value="&"/>
			                                                </c:if>
			                                            </c:forEach>
													</a>
												</p>
											</div>
											<span class="views"><i class="fa fa-headphones"></i> <fmt:formatNumber type="number" value="${song.totalViews}" /></span>
											<span class="home_audio audiojs" style="background:#565353;border-radius:15px"><a class="play"><i class="fa fa-play icon"></i></a></span>
										</li>
									</c:forEach>
								</ul>
							</div>
							<div class="col-sm-6">
								<ul class="songs" style="margin:0px">
									<c:forEach items="${homeSongs}" var="song" begin="1" end="20" step="2">
										<li>
											<div class="track-meta">
												<h5><a href="#" style="font-size:15px;"><c:out value="${song.name}" /></a></h5>
												<p>
													<a href="#" style="font-size:14px">
														<c:forEach items="${song.artists}" var="artist" varStatus="loop">
			                                                <c:out value="${artist.name}" />
			                                                <c:if test="${loop.index == 0 && loop.index lt fn:length(song.artists) - 1}">
			                                                	<c:out value="ft."/>
			                                                </c:if>
			                                                <c:if test="${loop.index > 0 && loop.index lt fn:length(song.artists) - 1}">
			                                                	<c:out value="&"/>
			                                                </c:if>
			                                            </c:forEach>
													</a>
												</p>
											</div>
											<span class="views"><i class="fa fa-headphones"></i> <fmt:formatNumber type="number" value="${song.totalViews}" /></span>
											<span class="home_audio audiojs" style="background:#565353;border-radius:15px"><a class="play"><i class="fa fa-play icon"></i></a></span>
										</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</c:if>
				</div>

				<!-- right side -->
				<div class="col-md-4 recent-blog">
					<div style="margin-top:300px;"></div>
					<!-- top songs -->
					<h2>Top <span>songs</span><a href="#" class="play-all"><i class="fa fa-play"></i></a></h2>
					<div class="widget">
						<!-- Nav tabs -->
						<ul class="tabs">
							<li class="active"><a href="#recent" data-toggle="tab">V-POP</a></li>
							<li><a href="#mostcomments" data-toggle="tab">US-UK</a></li>
							<li><a href="#popular" data-toggle="tab">K-POP</a></li>
						</ul>

						<!-- Tab panes -->
						<div class="tab-content">
							<div class="tab-pane fade in active" id="recent">
								<c:forEach items="${topVn}" var="sp" begin="0" end="0">								
									<div class="row recent-post">
										<div class="col-md-4 post-image" style="margin:15px 0">
											<a href="#">
												<img src="<c:url value="${sp.song.imageUrl}"/>" alt="Nulla rutrum felis eu interdum" />
												<span class="special-1">01</span>
											</a>
										</div>
										<div class="col-md-8 post-content">
											<h3><a href="blog-single.html" style="font-size:15px">${sp.song.name}</a></h3>
											<h4 class="post-meta" style="margin:0">
												<a href="#" class="comments" style="font-size:14px">
													<c:forEach items="${sp.song.artists}" var="artist" varStatus="loop">
		                                                <c:out value="${artist.name}" />
		                                                <c:if test="${loop.index == 0 && loop.index lt fn:length(song.artists) - 1}">
		                                                	<c:out value="ft."/>
		                                                </c:if>
		                                                <c:if test="${loop.index > 0 && loop.index lt fn:length(song.artists) - 1}">
		                                                	<c:out value="&"/>
		                                                </c:if>
		                                            </c:forEach>
												</a>
											</h4>
											<span class="author" style="font-size:12px">
												<i class="fa fa-headphones" style="opacity:0.6"></i>
												<fmt:formatNumber type="number" value="${sp.song.totalViews}" />
											</span>
										</div>
									</div>
								</c:forEach>
								<ul class="songs" style="margin-top:0">
									<c:forEach items="${topVn}" var="sp" begin="1" end="9" varStatus="loop">
										<li>
											<c:if test="${loop.index == 1}">
												<div class="track-nr" style="background:none;color:#1abc9c">2</div>
											</c:if>
											<c:if test="${loop.index == 2}">
												<div class="track-nr" style="background:none;color:#f39c12">3</div>
											</c:if>
											<c:if test="${loop.index > 2}">
												<div class="track-nr" style="background:none">${loop.index + 1}</div>
											</c:if>
											<div class="track-meta">
												<h5><a href="#" style="font-size:15px">${sp.song.name}</a></h5>
												<p>
													<a href=#" style="font-size:14px">
														<c:forEach items="${sp.song.artists}" var="artist" varStatus="loop">
			                                                <c:out value="${artist.name}" />
			                                                <c:if test="${loop.index == 0 && loop.index lt fn:length(song.artists) - 1}">
			                                                	<c:out value="ft."/>
			                                                </c:if>
			                                                <c:if test="${loop.index > 0 && loop.index lt fn:length(song.artists) - 1}">
			                                                	<c:out value="&"/>
			                                                </c:if>
			                                            </c:forEach>
													</a>
												</p>
											</div>
											<span class="views" style="float:right">
												<i class="fa fa-headphones" style="opacity:0.6"></i>
												<fmt:formatNumber type="number" value="${sp.song.totalViews}" />
											</span>
										</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</section>

	<!-- latest content section -->
	<section id="latest-content">
		<div class="container">
			<div class="row">
				<div class="col-md-6 latest-videos">
					<h2>Your <span>Playlists</span> <a href="#" class="view-all"><span>View All</span> <i class="fa fa-angle-double-right view-all-icon"></i></a></h2>
					<div class="row">
						<div class="col-xs-6 col-sm-4">
							<div class="latest-content">
								<a href="#">
									<div class="latest-content-image">
										<img src="<c:url value="/static/img/photo12.jpg"/>" alt="" />
									</div>
									<div class="latest-content-info">

										<div class="meta">
											<div class="icon">
												<i class="fa fa-video-camera"></i>
											</div>
											<h4>Simple Session</h4>
											<p>9-October-2013</p>
										</div>
									</div>
								</a>
							</div>
						</div>
						<div class="col-xs-6 col-sm-4">
							<div class="latest-content">
								<a href="#">
									<div class="latest-content-image">
										<img src="<c:url value="/static/img/photo12.jpg"/>" alt="" />
									</div>
									<div class="latest-content-info">
										<div class="meta">
											<div class="icon">
												<i class="fa fa-video-camera"></i>
											</div>
											<h4>Quantum Force</h4>
											<p>27-September-2013</p>
										</div>
									</div>
								</a>
							</div>
						</div>
						<div class="col-xs-6 col-sm-4">
							<div class="latest-content">
								<a href="#">
									<div class="latest-content-image">
										<img src="<c:url value="/static/img/photo12.jpg"/>" alt="" />
									</div>
									<div class="latest-content-info">

										<div class="meta">
											<div class="icon">
												<i class="fa fa-video-camera"></i>
											</div>
											<h4>Simple Session</h4>
											<p>9-October-2013</p>
										</div>
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6 latest-albums">
					<h2>Recommended<a href="#" class="view-all"><span>View All</span> <i class="fa fa-angle-double-right view-all-icon"></i></a></h2>
					<div class="row">
						<div class="col-xs-6 col-sm-4">
							<div class="latest-content">
								<a href="#">
									<div class="latest-content-image">
										<img src="<c:url value="/static/img/photo12.jpg"/>" alt="" />
									</div>
									<div class="latest-content-info">

										<div class="meta">
											<div class="icon">
												<i class="fa fa-headphones"></i>
											</div>
											<h4>Large Worldwide</h4>
											<p>Deep House</p>
										</div>
									</div>
								</a>
							</div>
						</div>
						<div class="col-xs-6 col-sm-4">
							<div class="latest-content">
								<a href="#">
									<div class="latest-content-image">
										<img src="<c:url value="/static/img/photo12.jpg"/>" alt="" />
									</div>
									<div class="latest-content-info">
										<div class="meta">
											<div class="icon">
												<i class="fa fa-headphones"></i>
											</div>
											<h4>Desire Evita</h4>
											<p>Hip-Hop, Pop/Rock</p>
										</div>
									</div>
								</a>
							</div>
						</div>
						<div class="col-xs-6 col-sm-4">
							<div class="latest-content">
								<a href="#">
									<div class="latest-content-image">
										<img src="<c:url value="/static/img/photo12.jpg"/>" alt="" />
									</div>
									<div class="latest-content-info">

										<div class="meta">
											<div class="icon">
												<i class="fa fa-headphones"></i>
											</div>
											<h4>Large Worldwide</h4>
											<p>Deep House</p>
										</div>
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</main>
<!-- end main content area -->