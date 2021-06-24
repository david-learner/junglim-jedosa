/**
 * 제본 유형 테이블에 새로운 제본 유형 행을 추가한다
 */
function addNewBindingType(clickedElement) {
    // 제본 유형 가격표
    let parent = clickedElement.parentElement.parentElement;
    // 제본 유형 가격표 마지막 행
    // 제본 유형 추가 버튼 제외. 테이블에 행이 존재하지 않을 경우 테이블 머리 열이 선택됨
    let lastRowName = parent.lastElementChild.previousElementSibling.getAttribute("name")
    if (lastRowName === "binding-type-price-table-new-item") {
        alert("저장되지 않은 제본 유형이 존재합니다.\n저장 후 추가해주세요.");
        return;
    }

    // 템플릿을 복사하여 제본 유형 가격 테이블 표에 새로운 행을 추가한다
    let copiedItem = document.getElementById("binding-type-price-table-new-item").cloneNode(true);
    copiedItem.style.display = "";
    // id, name을 삭제하여 새롭게 추가되는 행의 중복을 식별
    copiedItem.removeAttribute("id");
    copiedItem.removeAttribute("name");
    parent.insertBefore(copiedItem, parent.lastElementChild);
}

/**
 * 제본 유형 테이블에 새로운 제본 유형 행을 삭제한다
 */
function removeNewBindingType(clickedElement) {
    let row = clickedElement.parentElement.parentElement.parentElement.parentElement;
    row.remove();
}

/**
 * 제본 유형을 저장하고 제본 유형 테이블에 저장된 정보를 추가한다 
 */
function saveBindingType(clickedElement) {
    let row = clickedElement.parentElement.parentElement.parentElement.parentElement;
    let itemId = document.getElementById("item-id").value;
    let bindingTypeName = row.querySelector("input[name='binding-type-name']").value;
    let bindingTypePrice = row.querySelector("input[name='binding-type-price']").value;

    let bindingTypeData = {
        "itemId" : itemId,
        "name" : bindingTypeName,
        "price" : bindingTypePrice
    };

    let requestUrl = "/api/admin/items/" + itemId + "/binding/bindingtypes/save";

    $.ajax({
        url: requestUrl,
        dataType: "json",
        type: 'POST',
        data: JSON.stringify(bindingTypeData),
        processData: false,
        contentType: "application/json"
    }).done(function (data, textStatus, jqXHR) {
        alert("저장되었습니다");
        revealControllerForSaved(row);
        updateBindingTypeData(row, data);
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseJSON.message);
    });
}

/**
 * 새로운 제본유형 저장을 위한 컨트롤러는 제거되고 저장된 제본유형을 위한 컨트롤러가 보여진다
 * 입력을 위한 element들은 readonly로 변경된다
 */
function revealControllerForSaved(row) {
    row.querySelector("div[name='controllerForNew']").remove();
    row.querySelector("div[name='controllerForSaved']").style.display = "";
    toggleInputReadOnly(row);
}

/**
 * 제본 유형 열의 저장(for업데이트) 컨트롤러를 보여준다
 */
function revealUpdateController(clickedElement) {
    let row = clickedElement.parentElement.parentElement.parentElement.parentElement;
    // controller toggle
    row.querySelector("div[name='updateController']").style.display = "";
    row.querySelector("div[name='deleteController']").style.display = "none";
    toggleInputReadOnly(row);
}

/**
 * 제본 유형 열의 삭제 컨트롤러를 보여준다 
 */
function revealDeleteController(clickedElement) {
    let row = clickedElement.parentElement.parentElement.parentElement.parentElement;
    // controller toggle
    row.querySelector("div[name='updateController']").style.display = "none";
    row.querySelector("div[name='deleteController']").style.display = "";
    toggleInputReadOnly(row);
}

/**
 * 제본유형 입력 칸을 읽기전용 또는 수정 가능한 상태로 변경한다
 */
function toggleInputReadOnly(row) {
    let isNameReadOnly = row.querySelector("input[name='binding-type-name']").getAttribute("readonly");
    let isPriceReadOnly = row.querySelector("input[name='binding-type-price']").getAttribute("readonly");

    if (isNameReadOnly === null && isPriceReadOnly === null) {
        row.querySelector("input[name='binding-type-name']").setAttribute("readonly", "")
        row.querySelector("input[name='binding-type-price']").setAttribute("readonly", "")
    } else {
        row.querySelector("input[name='binding-type-name']").removeAttribute("readonly")
        row.querySelector("input[name='binding-type-price']").removeAttribute("readonly")
    }
}

/**
 * 특정 제본유형 가격표 행의 정보를 업데이트한다
 */
function updateBindingTypeData(row, data) {
    // data is JsonObject
    let id = data.id;
    let name = data.name;
    let price = data.price;
    row.querySelector("input[name='binding-type-id']").value = id;
    row.querySelector("input[name='binding-type-name']").value = name;
    row.querySelector("input[name='binding-type-price']").value = price;
}

/**
 * 제본유형 정보를 수정한다
 */
function updateBindingType(clickedElement) {
    let row = clickedElement.parentElement.parentElement.parentElement.parentElement;
    let itemId = document.getElementById("item-id").value;
    let bindingTypeId = row.querySelector("input[name='binding-type-id']").value;
    let bindingTypeName = row.querySelector("input[name='binding-type-name']").value;
    let bindingTypePrice = row.querySelector("input[name='binding-type-price']").value;

    let bindingTypeData = {
        "itemId" : itemId,
        "id" : bindingTypeId,
        "name" : bindingTypeName,
        "price" : bindingTypePrice
    };

    let requestUrl = "/api/admin/items/" + itemId + "/binding/bindingtypes/update";

    $.ajax({
        url: requestUrl,
        dataType: "json",
        method: 'PUT',
        data: JSON.stringify(bindingTypeData),
        processData: false,
        contentType: "application/json"
    }).done(function (data, textStatus, jqXHR) {
        alert("수정되었습니다");
        updateBindingTypeData(row, data);
        revealDeleteController(clickedElement);
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseJSON.message);
    });
}

/**
 * 제본유형 정보를 삭제한다
 */
function deleteBindingType(clickedElement) {
    let row = clickedElement.parentElement.parentElement.parentElement.parentElement;
    let itemId = document.getElementById("item-id").value;
    let bindingTypeId = row.querySelector("input[name='binding-type-id']").value;
    let bindingTypeName = row.querySelector("input[name='binding-type-name']").value;
    let inputName = prompt("정말 삭제하시겠습니까?\n삭제하려는 제본유형 이름을 적어주세요", "");
    if (bindingTypeName !== inputName) {
        alert("삭제하려는 제본유형 이름이 일치하지 않습니다.");
        return;
    }

    let requestUrl = "/api/admin/items/" + itemId + "/binding/bindingtypes/" + bindingTypeId;

    $.ajax({
        url: requestUrl,
        type: "DELETE",
    }).done(function (data, textStatus, jqXHR) {
        alert("삭제되었습니다");
        row.remove();
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseJSON.message);
    });
}
