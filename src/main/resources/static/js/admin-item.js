// 인쇄 및 제본 - 제본 유형
// 새로운 아이템 옵션을 테이블에 추가하기 위해 입력 폼을 생성한다
function addNewItemOptionForm(clickedElement) {
    // 추가하려는 아이템 옵션 가격표
    let table = clickedElement.parentElement.parentElement;
    let fullCategoryName = getFullCategoryName(table);
    // 추가하려는 아이템 옵션 가격표 마지막 행
    // 옵션 추가 버튼 제외. 테이블에 행이 존재하지 않을 경우 테이블 머리 열이 선택됨
    let lastRowName = table.lastElementChild.previousElementSibling.getAttribute("name")
    let nameForNotSaved = "new-item-option-template";

    if (fullCategoryName === 'paper/paper-types' || fullCategoryName === 'paper/paper-size-types') {
        nameForNotSaved = "new-key-type-item-option-template";
    }

    if (lastRowName === nameForNotSaved) {
        alert("저장되지 않은 아이템 유형이 존재합니다.\n저장 후 추가해주세요.");
        return;
    }

    // 템플릿을 복사하여 아이템 옵션 가격 테이블 표에 새로운 행을 추가한다
    let copiedItem = document.getElementById(nameForNotSaved).cloneNode(true);
    copiedItem.style.display = "";
    // id는 한 페이지당 하나만 존재해야 하므로 삭제한다
    copiedItem.removeAttribute("id");
    table.insertBefore(copiedItem, table.lastElementChild);
}

/**
 * 새로운 아이템 옵션 입력 폼을 제거한다
 */
function removeNewItemOptionForm(clickedElement) {
    let row = clickedElement.parentElement.parentElement.parentElement.parentElement;
    row.remove();
}

/**
 * 새로운 아이템 옵션 저장을 위한 컨트롤러는 제거되고 저장된 아이템 옵션을 위한 컨트롤러가 보여진다
 * 입력을 위한 element들은 readonly로 변경된다
 */
function revealControllerForSaved(row) {
    row.querySelector("div[name='controllerForNew']").remove();
    row.querySelector("div[name='controllerForSaved']").style.display = "";
    toggleInputReadOnly(row);
}

/**
 * 아이탬 옵션 열의 저장(업데이트) 컨트롤러를 보여준다
 */
function revealUpdateController(clickedElement) {
    let row = clickedElement.parentElement.parentElement.parentElement.parentElement;
    // controller toggle
    row.querySelector("div[name='updateController']").style.display = "";
    row.querySelector("div[name='deleteController']").style.display = "none";
    toggleInputReadOnly(row);
}

/**
 * 입력을 취소하고 옵션 아이템 열의 삭제 컨트롤러를 보여준다
 */
function revealDeleteController(clickedElement) {
    let row = clickedElement.parentElement.parentElement.parentElement.parentElement;
    restoreOriginalValue(row);
    // controller toggle
    row.querySelector("div[name='updateController']").style.display = "none";
    row.querySelector("div[name='deleteController']").style.display = "";
    toggleInputReadOnly(row);

    function restoreOriginalValue(row) {
        let fullCategoryName = getFullCategoryName(row.parentElement);

        let itemOptionNameElement = row.querySelector("input[name='item-option-name']");
        let originalItemOptionName = itemOptionNameElement.dataset.itemOptionName;
        row.querySelector("input[name='item-option-name']").value = originalItemOptionName;

        if (fullCategoryName !== 'paper/paper-types' && fullCategoryName !== 'paper/paper-size-types') {
            let itemOptionPriceElement = row.querySelector("input[name='item-option-price']");
            let originalItemOptionPrice = itemOptionPriceElement.dataset.itemOptionPrice;
            row.querySelector("input[name='item-option-price']").value = originalItemOptionPrice;
        }
    }
}

function getFullCategoryName(table) {
    let categoryName = table.dataset.itemOptionCategoryName;
    let subCategoryName = table.dataset.itemOptionSubCategoryName;
    let fullCategoryName = categoryName + '/' + subCategoryName;
    console.log('full category name is ' + fullCategoryName);
    return fullCategoryName;
}

/**
 * 아이템 옵션 입력 칸을 읽기전용 또는 수정 가능한 상태로 변경한다
 */
function toggleInputReadOnly(row) {
    let fullCategoryName = getFullCategoryName(row.parentElement);

    if(fullCategoryName === 'paper/paper-types' || fullCategoryName === 'paper/paper-size-types') {
        let inputOptionName = "item-option-name";
        let isNameReadOnly = row.querySelector("input[name='" + inputOptionName + "']").getAttribute("readonly");

        if (isNameReadOnly === null) {
            row.querySelector("input[name='" + inputOptionName + "']").setAttribute("readonly", "")
        } else {
            row.querySelector("input[name='" + inputOptionName + "']").removeAttribute("readonly")
        }
    }

    if (fullCategoryName !== 'paper/paper-types' && fullCategoryName !== 'paper/paper-size-types') {
        let inputOptionName = "item-option-name";
        let isNameReadOnly = row.querySelector("input[name='" + inputOptionName + "']").getAttribute("readonly");

        let inputOptionPrice = "item-option-price";
        let isPriceReadOnly = row.querySelector("input[name='" + inputOptionPrice + "']").getAttribute("readonly");

        if (isNameReadOnly === null && isPriceReadOnly === null) {
            row.querySelector("input[name='" + inputOptionName + "']").setAttribute("readonly", "")
            row.querySelector("input[name='" + inputOptionPrice + "']").setAttribute("readonly", "")
        } else {
            row.querySelector("input[name='" + inputOptionName + "']").removeAttribute("readonly")
            row.querySelector("input[name='" + inputOptionPrice + "']").removeAttribute("readonly")
        }
    }
}

/**
 * 특정 아이템 옵션 가격표 행의 정보를 업데이트한다
 */
function updateItemOptionData(row, data) {
    // data is JsonObject
    let id = data.id; // 최초 저장시 id를 화면 내 업데이트 해주기 위해서 필요하다
    let name = data.name;
    let itemOptionNameElement = row.querySelector("input[name='item-option-name']");
    let price = 0;
    let itemOptionPriceElement = null;
    let fullCategoryName = getFullCategoryName(row.parentElement)

    // update text value
    itemOptionNameElement.value = name;
    // update dataset
    row.dataset.itemOptionId = id;
    itemOptionNameElement.dataset.itemOptionName = name;

    if (fullCategoryName !== 'paper/paper-types' && fullCategoryName !== 'paper/paper-size-types') {
        price = data.price;
        itemOptionPriceElement = row.querySelector("input[name='item-option-price']");
        itemOptionPriceElement.value = price;
        itemOptionPriceElement.dataset.itemOptionPrice = price;
    }
}

/**
 * 아이템 옵션을 저장하고 테이블에 옵션 정보가 담긴 행을 추가한다
 */
function saveItemOption(clickedElement) {
    let row = clickedElement.parentElement.parentElement.parentElement.parentElement;
    let itemOptionCategoryName = row.parentElement.dataset.itemOptionCategoryName;
    let itemOptionSubCategoryName = row.parentElement.dataset.itemOptionSubCategoryName;
    let fullCategoryName = itemOptionCategoryName + '/' + itemOptionSubCategoryName;
    let itemId = document.getElementById("item-id").value;
    let itemOptionName = row.querySelector("input[name='item-option-name']").value;
    let itemOptionPrice = 0;
    let itemOptionData = {
        "itemId" : itemId,
        "name" : itemOptionName,
    };

    if (fullCategoryName !== 'paper/paper-types' && fullCategoryName !== 'paper/paper-size-types') {
        itemOptionPrice = row.querySelector("input[name='item-option-price']").value;
        itemOptionData = {
            "itemId" : itemId,
            "name" : itemOptionName,
            "price" : itemOptionPrice
        };

        // validation
        let allowOnlyNumbersRegex = new RegExp(/^[0-9]*$/);
        if (itemOptionPrice === "" || itemOptionPrice === undefined || !allowOnlyNumbersRegex.test(itemOptionPrice)) {
            alert("상품 옵션 가격은 숫자만 입력할 수 있습니다.");
            return;
        }
    }

    let requestUrl = "/api/admin/items/" + itemId + "/" + itemOptionCategoryName + "/" + itemOptionSubCategoryName;

    $.ajax({
        url: requestUrl,
        dataType: "json",
        type: 'POST',
        data: JSON.stringify(itemOptionData),
        processData: false,
        contentType: "application/json"
    }).done(function (data, textStatus, jqXHR) {
        alert("저장되었습니다");
        revealControllerForSaved(row);
        updateItemOptionData(row, data);
        removeRowNameForRecognizingSavedItemOption(row);
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseJSON.message);
    });
}

/**
 * 저장된 아이템 옵션 행(row)임을 알기 위해서 new item option에만 붙는 이름을 제거한다
 */
function removeRowNameForRecognizingSavedItemOption(element) {
    element.removeAttribute("name");
}

/**
 * 특정 아이템 옵션 정보를 수정한다
 */
function updateItemOption(clickedElement) {
    let row = clickedElement.parentElement.parentElement.parentElement.parentElement;
    let itemId = document.getElementById("item-id").value;
    let itemOptionCategoryName = row.parentElement.dataset.itemOptionCategoryName;
    let itemOptionSubCategoryName = row.parentElement.dataset.itemOptionSubCategoryName;
    let fullCategoryName = itemOptionCategoryName + '/' + itemOptionSubCategoryName;
    let itemOptionId = row.dataset.itemOptionId;
    let itemOptionName = row.querySelector("input[name='item-option-name']").value;
    let itemOptionPrice = 0;
    let itemOptionData = {
        "itemId" : itemId,
        "id" : itemOptionId,
        "name" : itemOptionName,
    };

    if (fullCategoryName !== 'paper/paper-types' && fullCategoryName !== 'paper/paper-size-types') {
        itemOptionPrice = row.querySelector("input[name='item-option-price']").value;

        itemOptionData = {
            "itemId" : itemId,
            "id" : itemOptionId,
            "name" : itemOptionName,
            "price" : itemOptionPrice
        };
    }

    let requestUrl = "/api/admin/items/" + itemId + "/" + itemOptionCategoryName + "/" + itemOptionSubCategoryName + "/" + itemOptionId;

    $.ajax({
        url: requestUrl,
        dataType: "json",
        method: 'PUT',
        data: JSON.stringify(itemOptionData),
        processData: false,
        contentType: "application/json"
    }).done(function (data, textStatus, jqXHR) {
        alert("수정되었습니다");
        updateItemOptionData(row, data);
        revealDeleteController(clickedElement);
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseJSON.message);
    });
}

/**
 * 특정 아이템 옵션을 삭제한다
 */
function deleteItemOption(clickedElement) {
    let row = clickedElement.parentElement.parentElement.parentElement.parentElement;
    let itemId = document.getElementById("item-id").value;
    let itemOptionCategoryName = row.parentElement.dataset.itemOptionCategoryName;
    let itemOptionSubCategoryName = row.parentElement.dataset.itemOptionSubCategoryName;
    let itemOptionId = row.dataset.itemOptionId;
    let itemOptionName = row.querySelector("input[name='item-option-name']").value;
    let inputName = prompt("정말 삭제하시겠습니까?\n삭제하려는 상품 옵션의 이름을 적어주세요", "");
    if (itemOptionName !== inputName) {
        alert("삭제하려는 상품 옵션의 이름과 일치하지 않습니다.");
        return;
    }

    let requestUrl = "/api/admin/items/" + itemId + "/"+ itemOptionCategoryName + "/" + itemOptionSubCategoryName + "/" + itemOptionId;

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
