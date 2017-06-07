
$(document).ready(function() {
	
	$('#loginButton').click(function(data) {
		
	
	
		$('#loginFailResponse').html('');
		
		$.ajax({
			type : 'POST',
			url : 'login',
			
			data : $('#loginForm').serialize(),

			success : function(data) {
				var url="dashboard.jsp";
				//var url ="url.slice( 0, url.indexOf('&') );";
				url.slice( 0, url.indexOf('?') );

				window.location.href = url;
				
							
				
			},
			error : function(data) {
				
				if(data.status===401){
					 $('#loginFailResponse').text("Username or Password cannot be blank");
				}
				else if(data.status===402){
					 $('#loginFailResponse').text("Username or Password is incorrect");
				}
				
				else if(data.status===403){
					 $('#loginFailResponse').text("User Already Logged In..!!");
				}
			}

		});
	});

//	 $('#loginButton').click(function(data) {
//	        
//		    
//		    
//	        $('#loginFailResponse').html('');
//	        
//	        $.ajax({
//	             headers: { 
//	                    'Accept': 'application/json',
//	                    'Content-Type': 'application/json' 
//	                },
//	            dataType: "json",
//	            type : 'POST',
//	                        url : 'REST/WebService/login?',
//	            data : {
//	                userName : $('#userName').val(),
//	                password : $('#password').val()
//	            },
//	            success : function(data) {
//	              
//	                var url="dashboard.jsp";
//	                //var url ="url.slice( 0, url.indexOf('&') );";
//	                url.slice( 0, url.indexOf('?') );
//
//	                window.location.href = url;
//	                
//	                            
//	                
//	            },
//	            error : function(data) {
//	                
//	                if(data.status===401){
//	                     $('#loginFailResponse').text("Username or Password cannot be blank");
//	                }
//	                else if(data.status===402){
//	                     $('#loginFailResponse').text("Username or Password is incorrect");
//	                }
//	            }
//
//	        });
//	    });
	
});