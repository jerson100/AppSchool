((window,document)=>{
	document.addEventListener('DOMContentLoaded',evt=>{
		'use-strict'
		
		let player;
	    
		document.addEventListener('click',e=>{
			if(e.target.tagName === 'A'){
				let href = e.target.href;
				let youtubeId = youtube_parser(href);
				let title = e.target.parentElement.parentElement.querySelector(".table-data__column:nth-child(2)").textContent;
				if(youtubeId != false){
					console.log(youtubeId);
					if(document.querySelector(".mod--youtube")){
						player.loadVideoById(youtubeId,0)
				    	document.querySelector(".mod--youtube").classList.add("mod--active");
				    }else{
				    	createModalYoutube(youtubeId,title);
				    }
					e.preventDefault();
				}
			}
		});
		
		function youtube_parser(url){
		    var regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/;
		    var match = url.match(regExp);
		    return (match&&match[7].length==11)? match[7] : false;
		}
		
		function createModalYoutube(youtubeId, title){
			
			const modalContainer = document.createElement("div");
			modalContainer.setAttribute('class','mod mod--active mod--youtube');
			
			modalContainer.innerHTML = `
				<div class="mod__inner">
					<i class="mod__close mod__close--light fas fa-close"></i>
					<div class="youtube">
						<div class="youtube__inner">
							<h1 class="youtube__title">${title}</h1>
							<div class="youtube__wrapper">
								<div class="youtube__video" id="youtube__video"></div>
							</div>
						</div>					
					</div>
				</div>
			`;
			
			document.body.appendChild(modalContainer);
			
			modalContainer.querySelector(".mod__close").addEventListener('click',e=>{
				
				modalContainer.classList.remove("mod--active");
				
				player.stopVideo();
				/*
				if(document.querySelector(".api_frame_youtube")){
			    	document.querySelector(".api_frame_youtube").remove();
			    }*/
				
			});
			
			createYoutubeIframe(youtubeId, "youtube__video");
			
		};
		
		function loadApiPlayer(){
			let tag = document.createElement('script');
		    tag.src = "https://www.youtube.com/iframe_api";
		    tag.classList.add("api_frame_youtube");
		    
		    //obtenemos el script actual
		    let firstScriptTag = document.getElementsByTagName('script')[0];

		    //obtenemos el padre de este script y insertamos el elemento tag
		    //antes de este script
		    firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
		}
		
		function createYoutubeIframe(youtubeId, container){
			
			loadApiPlayer();
			
			//Ahora creamos el player
		    
		    window.onYouTubeIframeAPIReady = function(){
		        player = new YT.Player(container, {
		            videoId: youtubeId,
		            playerVars: {
		            	modestbranding: 1,
		                rel: 0
		            },
		            events: {
		                'onReady': onPlayerReady,
		                'onStateChange': onPlayerStateChange
		            }
		        });
		    }


		    // Se llama a esta funcióin cuando la api está lista
		    function onPlayerReady(event) {
		        event.target.playVideo();
		    }

		    // 5. La pai llama a esta función cuando se realiza algunos cambios de estado o 
		    // interacciones con el player
		    function onPlayerStateChange(event) {
		        if (event.data == YT.PlayerState.PLAYING) {
		            console.log("Video iniciado");
		        }
		    }

		    function stopVideo() {
		        player.stopVideo();
		    }
			
		    return player;
		}
		
		
	});
})(window,document);