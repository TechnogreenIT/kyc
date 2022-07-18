$(document).ready(function () {
	loadTodo();
});

function makeTodoModal(){
	var modelTitle = "New Todo";

	var bodyForm = ' <div class="form-group">'+
        					 '<input type="text" id="newTodoText" class="form-control" placeholder="What do you want to do?">'+
        					 '</div>'+
        					 '<div class="form-group select2-parent">'+
        						 '<select class="select2">'+
        							'<option>Select a Label</option>'+
        							'<option>#Messages</option>'+
        							'<option>#Clients</option>'+
        							'<option>#Server</option>'+
        							'<option>#Marketing</option>'+
        							'<option>#Work Related</option>'+
        							'<option>#Website</option>'+
        						'</select>'+
        					 '</div>'+
        					 '<div class="form-group mb-0">'+
        						 '<label>Set Priority</label>'+
        						 '<div class="btn-group btn-group-toggle btn-group-justified" data-toggle="buttons">'+
        							'<label class="btn active">'+
        							'<input type="radio" name="options" id="option1" autocomplete="off" checked> Low'+
        							'</label>'+
        							'<label class="btn">'+
        							'<input type="radio" name="options" id="option2" autocomplete="off"> Moderate'+
        							'</label>'+
        							'<label class="btn">'+
        							'<input type="radio" name="options" id="option3" autocomplete="off"> High'+
        							'</label>'+
        						 '</div>'+
        					 '</div>' ;
	$.showModal({
		title: modelTitle,
		modalDialogClass: '',
		body: bodyForm,
		footer: '<button class="btn btn-success btn--icon-text" onclick="savedata(this)"><i class="zmdi zmdi-save"></i> Save</button><button class="btn btn-warning btn--icon-text" data-dismiss="modal"><i class="zmdi zmdi-close"></i> Close</button>',
		onCreate: function (modal) {
			// create event handler for form submit and handle values

		}
	})
	makeSelect2();
}

function loadTodo(){
		 $.ajax({
				url: 'ajax-load-todos',
				type: 'post',
				dataType:'text',
				success: function(data){
				 if (data != null && data.length != 0) {
					var parseData = JSON.parse(data);
					$('.todo_div').empty(); //Clear div when new load
					$.each(parseData, function(index, element) {
						
						var html = $("<div class='listview__item'><div class='checkbox checkbox--char todo__item'>"
								+"<input type='checkbox' id='custom-checkbox-"+element.todoId+"'>"
								+"<label class='checkbox__char bg-blue' for='custom-checkbox-"+element.todoId+"'>O</label>"
								+"<div class='listview__content'>"
								+"<div class='listview__heading'>"+element.todoText+"</div>"
								+"<p>"+element.todoDate+"</p>"
								+"</div>"
								+"<div class='listview__attrs'>"
								+"<span>#Messages</span>"
								+"<span>High</span>"
								+"</div></div>"
								+"<div class='actions listview__actions'>"
								+"<div class='dropdown actions__item black-color'>"
								+"<i class='zmdi zmdi-more-vert' data-toggle='dropdown'></i>"
								+"<div class='dropdown-menu dropdown-menu-right'>"
								+"<a class='dropdown-item' onclick='updateData("+element.todoId+")'>Mark as completed</a>"
								+"<a class='dropdown-item' onclick='deleteData("+element.todoId+")'>Delete</a>"
								+"</div></div></div></div>")
								
						$(".todo_div").append(html);
						 $(".todo_div").css("max-height", "500px");
						 $(".todo_div").css("overflow-y", "scroll");
					});
				 }
				  //$('#attachRes').html(response);   //select the id and put the response in the html
				},
			   error: function(jqXHR, textStatus, errorThrown){
				  console.log('error(s):'+textStatus, errorThrown);
			   }
			});
}

function updateData(id){
	$.ajax({
		url: 'ajax-add-todo',
		type: 'post',
		dataType:'text',   //expect return data as html from server
		data: ({
			action : 'archive',
			id : id
        }),
		success: function(data){//alert(response);
			 if (data != null) {
				  loadTodo();
			}
		},
	   error: function(jqXHR, textStatus, errorThrown){
		  console.log('error(s):'+textStatus, errorThrown);
	   }
	});
}
function deleteData(id){
	$.ajax({
		url: 'ajax-add-todo',
		type: 'post',
		dataType:'text',   //expect return data as html from server
		data: ({
			action : 'delete',
			id : id
        }),
		success: function(data){//alert(response);
			 loadTodo();
		},
	   error: function(jqXHR, textStatus, errorThrown){
		  console.log('error(s):'+textStatus, errorThrown);
	   }
	});
}  



function savedata(el) {
  var todo = $('#newTodoText').val();
  $.ajax({
    url: 'ajax-add-todo',
    type: 'post',
    dataType: 'text', // expect return data as html from server
    data: ({
      action: 'add',
      todo: todo
    }),

    success: function(data) {
      var modalId = $(el).closest('.modal').attr('id');
      $('#' + modalId).modal('toggle');
      if (data != null) {
        loadTodo();
      }
    },
    error: function(data) {
      console.log('error(s):' + data);
    }
  });
}