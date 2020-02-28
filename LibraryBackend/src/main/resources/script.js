var selectedRow = null

window.onload = function() {
	var request = new XMLHttpRequest();
	request.open('GET', 'localhost:8080/books');
    request.responseType = 'application/json';
	request.onload = function() {
       	populateTable(request.response);
    };
    request.send();
}

function populateTable(booksList) {
	for (var i = 0; i < booksList.length; i++) {
		insertNewRecord(booksList[i])
	}
}

function onFormSubmit() {
    if (validate()) {
        var formData = readFormData();
        var request = new XMLHttpRequest();
        if (selectedRow == null) {
        	request.open('POST', 'localhost:8080/books');
        	request.responseType = 'application/json';
			request.onload = function() {
            	insertNewRecord(request.response);
            };
            request.send(formData);
        }
        else {
        	var bookId = selectedRow.cells[0].innerHTML;
        	request.open('PUT', 'localhost:8080/books/' + bookId);
        	request.responseType = 'application/json';
			request.onload = function() {
            	updateRecord(request.response);
            };
            request.send(formData);
        }
        resetForm();
    }
}

function readFormData() {
    var formData = {};
    formData["title"] = document.getElementById("title").value;
    formData["author"] = document.getElementById("author").value;
    formData["genre"] = document.getElementById("genre").value;
    formData["status"] = document.getElementById("status").value;
    return formData;
}

function insertNewRecord(data) {
    var table = document.getElementById("books").getElementsByTagName('tbody')[0];
    var newRow = table.insertRow(table.length);
    cell1 = newRow.insertCell(0);
    cell1.innerHTML = data.id;
    cell2 = newRow.insertCell(1);
    cell2.innerHTML = data.title;
    cell3 = newRow.insertCell(2);
    cell3.innerHTML = data.author;
    cell4 = newRow.insertCell(3);
    cell4.innerHTML = data.genre;
    cell4 = newRow.insertCell(4);
    cell4.innerHTML = data.status;
    cell4 = newRow.insertCell(5);
    cell4.innerHTML = `<a onClick="onEdit(this)">Edit</a>
                       <a onClick="onDelete(this)">Delete</a>`;
}

function resetForm() {
    document.getElementById("title").value = "";
    document.getElementById("author").value = "";
    document.getElementById("genre").value = "";
    document.getElementById("status").value = "";
    selectedRow = null;
}

function onEdit(td) {
    selectedRow = td.parentElement.parentElement;
    document.getElementById("title").value = selectedRow.cells[1].innerHTML;
    document.getElementById("author").value = selectedRow.cells[2].innerHTML;
    document.getElementById("genre").value = selectedRow.cells[3].innerHTML;
    document.getElementById("status").value = selectedRow.cells[4].innerHTML;
}

function updateRecord(data) {
    selectedRow.cells[1].innerHTML = data.fullName;
    selectedRow.cells[2].innerHTML = data.empCode;
    selectedRow.cells[3].innerHTML = data.salary;
    selectedRow.cells[4].innerHTML = data.city;
}

function onDelete(td) {
    if (confirm('Delete this book?')) {
        row = td.parentElement.parentElement;
        var request = new XMLHttpRequest();
        request.open('DELETE', 'localhost:8080/books/' + row.cells[0].innerHTML);
        request.responseType = 'application/json';
		request.onload = function() {
           	document.getElementById("books").deleteRow(row.rowIndex);
        };
        request.send();
        resetForm();
    }
}
function validate() {
    isValid = true;
    if (document.getElementById("title").value == "") {
        isValid = false;
        document.getElementById("mandatoryError").classList.remove("hide");
    } else {
        isValid = true;
        if (!document.getElementById("mandatoryError").classList.contains("hide"))
            document.getElementById("mandatoryError").classList.add("hide");
    }
    return isValid;
}
