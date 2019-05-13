 $(function(){
         requestForNav();
      });
       
      function requestForNav() {
		 $.ajax({
		        url:"/AgricultureSale0.1/category/all.action",
		        type:"post",
		        dataType:"json",
		        async:false,
		        cache:false,
		        success: function(res) {
                  addNav(res);
     	        }
		     });
      }
      
      function addNav(res) {
         var ele = $("#nav_list")[0];
         var child1;
         var child2;
         var child3;
         var appendHTML="";
         for(var i=0;i<res.length;i++) {
            child1 = res[i]["childs"];
             appendHTML +=  "<li>"+
                    "<a href='#' class='game_hover'>"+ res[i]["name"] +"</a>"+
                   
                   " <div class='hover_cont wlyx'>";
                    
            for(var j=0;j<child1.length;j++) {
               child2 = child1[j]["childs"];
               appendHTML+=  " <div class='nav_cont'>"
                         + "   <div class='nav_li'>" 
                         + "   <div class='nav_li_l'>"
                                    + child1[j]["name"] +
                             "   </div>";
               for(var k=0;k<child2.length;k++) {
                  child3 = child2[k];
                  appendHTML+=   "<div class='nav_li_r'>"
                                    +"<a href='commodity/findByCategory.action?cate_id="+child3["id"]+"&currPage=1'>"+ child3["name"] +"</a>┊"
                                +"</div>";
                    
               }
               appendHTML +=    " </div>"
                        +"</div>";
            }
            appendHTML+= "</div>" 
           			  +"</li>";
         }
         ele.innerHTML = appendHTML;
         setMainNav();
      }
   function moveIn(ele) {
     ele.style.border = "3px solid #FFCFB1" ;
   }
   
   function moveOut(ele) {
     ele.style.border = "3px solid #ffffff" ;
   }