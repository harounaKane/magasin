//MOINS
$(".moins").click( function(e) {
	e.preventDefault();
	var id_panier = $(this).attr("id");
	
	//RECUP VALEUR INPUT
	var input = $("#input" + id_panier);
	var quantityInput = input.val() - 1;
	
	console.log( typeof quantityInput )
	
	if( quantityInput <= 0 )
		quantityInput = 1
		
	input.val(quantityInput);
	
	$.ajax({
		url: "/update/panier/"+id_panier+"/"+quantityInput,
		type: 'POST',
		data: {id_panier: id_panier, qtt: quantityInput},
		success: function(result){
			updateTotal(result);
		}
	});
	
} );


//PLUS
$(".plus").click( function(e) {
	e.preventDefault();

	//RECUP VALEUR INPUT
	var id_panier = $(this).attr("pid");
	var input = $("#input" + id_panier);
	var quantityInput = parseInt(input.val()) + 1;

	console.log( typeof quantityInput )
	//RECUP QTT DISPO
	var quantity = parseInt( $("#quantity" + id_panier).text() );
	
	if( quantityInput > quantity )
		quantityInput = quantity; 
		
	input.val(quantityInput);
	
	$.ajax({
		url: "/update/panier/"+id_panier+"/"+quantityInput,
		type: 'POST',
		data: {id_panier: id_panier, qtt: quantityInput},
		success: function(result){
			updateTotal(result);
		}
	});
	
} );


function updateTotal(panier){
	var sommeArt = $("#somme" + panier.id);
	var t = panier.quantity*panier.article.prix;
	console.log( typeof t )
	sommeArt.text( t );
	sommeTotalePanier();
}

var total = 0.0;
var sommeElt = document.getElementsByClassName("somme");

function sommeTotalePanier(){
	//SOMME TOTALE PANIER
 	
  total = 0.0;

	for(i=0; i<sommeElt.length; i++){
		sommeElt[i].innerHTML = Math.round( sommeElt[i].innerText * 100 ) / 100;
		total += parseFloat( sommeElt[i].innerHTML );
	}
	console.log( typeof total )
	document.getElementById('resultat').innerHTML = new Intl.NumberFormat().format(total);
	//document.getElementById('panierModal').innerHTML = new Intl.NumberFormat().format(total);
}

sommeTotalePanier();

document.getElementById('modal').addEventListener('click', () => {
	document.getElementById('panierModal').innerHTML = new Intl.NumberFormat().format(total);
})









