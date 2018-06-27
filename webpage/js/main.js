function maxHeightOfRows(table, numOfRows){
  var curMax = 0;
  for(var i = 1; i < numOfRows; i++){
    if(table.rows[i].offsetHeight > curMax){
      curMax = table.rows[i].offsetHeight;
    }
  }
  return curMax;
}

function changeStatusHeights(stat, height){
  for(var i = 0; i < stat.length; i++){
    stat[i].style.height = height + 'px';  
  }
}

function changeHeight(){
  for(var j = 1; j < 5; j++){
    var table = document.getElementById("metrics-" + j);
    var numOfRows = table.rows.length;
    var curMax = maxHeightOfRows(table, numOfRows);
  
    // Change the height of table rows
    for(var i = 1; i < numOfRows; i++){
        table.rows[i].height = curMax;
    }
  
    // Change div heights of status
    changeStatusHeights(document.getElementById("metrics-" + j).getElementsByClassName("warning_status"), curMax);
    changeStatusHeights(document.getElementById("metrics-" + j).getElementsByClassName("alert_status"), curMax);
    changeStatusHeights(document.getElementById("metrics-" + j).getElementsByClassName("green_status"), curMax);  
  }
}
