console.log("monScrpospt");

//var ins = document.getElementsByClassName ("inscription");
var ins = document.querySelectorAll ("nav a");
console.log(ins);

for(var pos=0; pos<ins.length; pos++){

	ins[pos].addEventListener("mouseover", function(e){
		this.classList.toggle('bc')
	});
	
	ins[pos].addEventListener("mouseleave", function(e){
		this.classList.toggle('bc')
	});
}


