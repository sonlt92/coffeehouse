$(document).ready(function(){
  $(".read-more").click(function(){
    $(this).prev().toggle();
    $(this).siblings('.dots').toggle();
    if($(this).text()=='Xem thêm'){
      $(this).text('Xem thêm');
    }
    else{
      $(this).text('Xem thêm');
    }
  });
});
