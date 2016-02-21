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
					<div id="jssor_1" style="position: relative; margin: 0 auto; top: 0px; left: 0px; width: 600px; height: 300px; overflow: hidden; visibility: hidden;">
						<!-- Loading Screen -->
						<div data-u="loading" style="position: absolute; top: 0px; left: 0px;">
							<div style="filter: alpha(opacity=70); opacity: 0.7; position: absolute; display: block; top: 0px; left: 0px; width: 100%; height: 100%;"></div>
							<div style="position:absolute;display:block;background:url('static/img/loading.gif') no-repeat center center;top:0px;left:0px;width:100%;height:100%;"></div>
						</div>
						<div data-u="slides" style="cursor: default; position: relative; top: 0px; left: 0px; width: 600px; height: 300px; overflow: hidden;">
							<div data-p="112.50" style="display: none;">
								<a href="#"><img data-u="image" src="<c:url value="/static/img/003.jpg"/>" /></a>
								<img data-u="thumb" src="<c:url value="/static/img/003.jpg"/>" />
							</div>
							<div data-p="112.50" style="display: none;">
								<a href="#"><img data-u="image" src="<c:url value="/static/img/003.jpg"/>" /></a>
								<img data-u="thumb" src="<c:url value="/static/img/003.jpg"/>" />
							</div>
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
					
					<!-- playlist|album --> 
					<h2>Latest <span>Albums</span> <a href="#" class="view-all"><span>View All</span> <i class="fa fa-angle-double-right view-all-icon"></i></a></h2>
					<div class="row">
						<div class="col-xs-6 col-sm-3">
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
											<h4>Large So long Title More and more</h4>
											<p>Deep</p>
										</div>
									</div>
								</a>
							</div>
						</div>
						<div class="col-xs-6 col-sm-3">
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
						<div class="col-xs-6 col-sm-3">
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
						<div class="col-xs-6 col-sm-3">
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
						<div class="col-xs-6 col-sm-3">
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
						<div class="col-xs-6 col-sm-3">
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
						<div class="col-xs-6 col-sm-3">
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
						<div class="col-xs-6 col-sm-3">
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
					</div>

					<!-- song -->
					<h2>Latest <span>Songs</span> <a href="#" class="view-all"><span>View All</span> <i class="fa fa-angle-double-right view-all-icon"></i></a></h2>
					<div class="row">
						<div class="col-sm-6">
							<ul class="songs" style="margin:0px">
								<li>
									<div class="track-meta">
										<h5><a href="#">Nobody Move</a></h5>
										<p><a href="#">Etiam</a></p>
									</div>
									<span class="views"><i class="fa fa-headphones"></i> 9.999.999</span>
								</li>
								<li>
									<div class="track-meta">
										<h5><a href="#">Nobody Move</a></h5>
										<p><a href="#">Etiam</a></p>
									</div>
									<span class="views"><i class="fa fa-headphones"></i> 9.999.999</span>
								</li>
								<li>
									<div class="track-meta">
										<h5><a href="#">Nobody Move</a></h5>
										<p><a href="#">Etiam</a></p>
									</div>
									<span class="views"><i class="fa fa-headphones"></i> 9.999.999</span>
								</li>
								<li>
									<div class="track-meta">
										<h5><a href="#">Nobody Move</a></h5>
										<p><a href="#">Etiam</a></p>
									</div>
									<span class="views"><i class="fa fa-headphones"></i> 9.999.999</span>
								</li>
							</ul>
						</div>
						<div class="col-sm-6">
							<ul class="songs" style="margin:0px">
								<li>
									<div class="track-meta">
										<h5><a href="#">Nobody Move</a></h5>
										<p><a href="#">Etiam</a></p>
									</div>
									<span class="views"><i class="fa fa-headphones"></i> 9.999.999</span>
								</li>
								<li>
									<div class="track-meta">
										<h5><a href="#">Nobody Move</a></h5>
										<p><a href="#">Etiam</a></p>
									</div>
									<span class="views"><i class="fa fa-headphones"></i> 9.999.999</span>
								</li>
								<li>
									<div class="track-meta">
										<h5><a href="#">Nobody Move</a></h5>
										<p><a href="#">Etiam</a></p>
									</div>
									<span class="views"><i class="fa fa-headphones"></i> 9.999.999</span>
								</li>
								<li>
									<div class="track-meta">
										<h5><a href="#">Nobody Move</a></h5>
										<p><a href="#">Etiam</a></p>
									</div>
									<span class="views"><i class="fa fa-headphones"></i> 9.999.999</span>
								</li>
							</ul>
						</div>
					</div>
				</div>

				<!-- right side -->
				<div class="col-md-4 recent-blog">
					<div style="margin-top:300px;"></div>
					<!-- top songs -->
					<h2>Top <span>songs</span><a href="#" class="play-all"><i class="fa fa-play"></i></a></h2>
					<div class="widget">
						<!-- Nav tabs -->
						<ul class="tabs">
							<li class="active"><a href="#recent" data-toggle="tab">Recent</a></li>
							<li><a href="#mostcomments" data-toggle="tab">Comments</a></li>
							<li><a href="#popular" data-toggle="tab">Popular</a></li>
						</ul>

						<!-- Tab panes -->
						<div class="tab-content">
							<div class="tab-pane fade in active" id="recent">
								<div class="row recent-post">
									<div class="col-md-4 post-image" style="margin:15px 0">
										<a href="#">
											<img src="<c:url value="/static/img/photo12.jpg"/>" alt="Nulla rutrum felis eu interdum" />
											<span class="special-1">01</span>
										</a>
									</div>
									<div class="col-md-8 post-content">
										<h3><a href="blog-single.html">Lorem Ipsum has been the industry's standard dummy text ever since</a></h3>
										<h4 class="post-meta" style="margin:0">
											<a href="#" class="comments">John Doe</a>
										</h4>
										<span class="author" style="font-size:12px"><i class="fa fa-headphones"></i> 9.999.999</span>
									</div>
								</div>
								<ul class="songs" style="margin-top:0">
									<li>
										<div class="track-nr" style="background:none;color:#1abc9c">2</div>
										<div class="track-meta">
											<h5><a href="#">Nobody Move</a></h5>
											<p><a href=#">Etiam</a></p>
										</div>
										<span class="views"><i class="fa fa-headphones"></i> 9.999.999</span>
									</li>
									<li>
										<div class="track-nr" style="background:none;color:#f39c12">3</div>
										<div class="track-meta">
											<h5>Miss Kitten</h5>
											<p>Etiam</p>
										</div>
										<span class="views"><i class="fa fa-headphones"></i> 9.999.999</span>
									</li>
									<li>
										<div class="track-nr" style="background:none">4</div>
										<div class="track-meta">
											<h5>Ignissim Congue</h5>
											<p>Etiam</p>
										</div>
										<span class="views"><i class="fa fa-headphones"></i> 9.999.999</span>
									</li>
									<li>
										<div class="track-nr" style="background:none">5</div>
										<div class="track-meta">
											<h5>Nobody Move</h5>
											<p>Etiam</p>
										</div>
										<span class="views"><i class="fa fa-headphones"></i> 9.999.999</span>
									</li>
									<li>
										<div class="track-nr" style="background:none">6</div>
										<div class="track-meta">
											<h5>Miss Kitten</h5>
											<p>Etiam</p>
										</div>
										<span class="views"><i class="fa fa-headphones"></i> 9.999.999</span>
									</li>
									<li>
										<div class="track-nr" style="background:none">7</div>
										<div class="track-meta">
											<h5>Ignissim Congue</h5>
											<p>Etiam</p>
										</div>
										<span class="views"><i class="fa fa-headphones"></i> 9.999.999</span>
									</li>
									<li>
										<div class="track-nr" style="background:none">8</div>
										<div class="track-meta">
											<h5>Nobody Move</h5>
											<p>Etiam</p>
										</div>
										<span class="views"><i class="fa fa-headphones"></i> 9.999.999</span>
									</li>
									<li>
										<div class="track-nr" style="background:none">9</div>
										<div class="track-meta">
											<h5>Miss Kitten</h5>
											<p>Etiam</p>
										</div>
										<span class="views"><i class="fa fa-headphones"></i> 9.999.999</span>
									</li>
									<li>
										<div class="track-nr" style="background:none">10</div>
										<div class="track-meta">
											<h5>Ignissim Congue</h5>
											<p>Etiam</p>
										</div>
										<span class="views"><i class="fa fa-headphones"></i> 9.999.999</span>
									</li>
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