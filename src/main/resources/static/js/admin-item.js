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

    // 종이 유형, 종이 규격 추가시
    if (fullCategoryName === 'paper/paper-types' || fullCategoryName === 'paper/paper-size-types') {
        nameForNotSaved = "new-key-type-item-option-template";
    }

    // 표지 종이 유형 추가시
    if (fullCategoryName === 'cover/paper-types' || fullCategoryName === 'content/paper-types') {
        nameForNotSaved = "new-paper-printing-type-item-option-template";
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

        if (fullCategoryName === 'flyleaf/flyleaf-content') {

            let itemOptionPriceElement = row.querySelector("input[name='item-option-price']");
            let originalItemOptionPrice = itemOptionPriceElement.dataset.itemOptionPrice;
            row.querySelector("input[name='item-option-price']").value = originalItemOptionPrice;
        }

        if (fullCategoryName === 'paper/paper-types' || fullCategoryName === 'paper/paper-size-types') {

            let itemOptionNameElement = row.querySelector("input[name='item-option-name']");
            let originalItemOptionName = itemOptionNameElement.dataset.itemOptionName;
            row.querySelector("input[name='item-option-name']").value = originalItemOptionName;
        }

        if (fullCategoryName === 'cover/paper-types' || fullCategoryName === 'content/paper-types') {
            // Name SELECT 옵션 복구 시작
            let selectElement = row.querySelector("select[name='item-option-name']");
            let originalItemOptionName = selectElement.dataset.itemOptionName;
            let options = selectElement.options;
            let size = selectElement.length;
            let indexMatchedName = null;

            // 원본 값(종이 유형 이름)과 일치하는 이름을 가진 option의 index를 찾는다
            for (let index = 0; index < size; index++) {

                if (options[index].value === originalItemOptionName) {
                    indexMatchedName = index;
                    break;
                }
            }

            options[indexMatchedName].selected = "selected";
            // Name SELECT 옵션 복구 끝

            // Size SELECT 옵션 복구 시작
            selectElement = row.querySelector("select[name='item-option-size']");
            let originalItemOptionSize = selectElement.dataset.itemOptionSize;
            options = selectElement.options;
            size = selectElement.length;
            indexMatchedName = null;

            // 원본 값(종이 유형 이름)과 일치하는 이름을 가진 option의 index를 찾는다
            for (let index = 0; index < size; index++) {

                if (options[index].value === originalItemOptionSize) {
                    indexMatchedName = index;
                    break;
                }
            }

            // 원본 값(종이 유형 이름)과 일치하는 option에 selected를 적용하여 원본 값과 일치하는 option이 선택되게 한다
            options[indexMatchedName].selected = "selected";
            // Size SELECT 옵션 복구 끝

            // Printing Color SELECT 옵션 복구 시작
            selectElement = row.querySelector("select[name='item-option-printing-color']");
            let originalItemOptionPrintingColor = selectElement.dataset.itemOptionPrintingColor;
            options = selectElement.options;
            size = selectElement.length;
            indexMatchedName = null;

            // 원본 값(종이 유형 이름)과 일치하는 이름을 가진 option의 index를 찾는다
            for (let index = 0; index < size; index++) {

                if (options[index].value === originalItemOptionPrintingColor) {
                    indexMatchedName = index;
                    break;
                }
            }

            // 원본 값(종이 유형 이름)과 일치하는 option에 selected를 적용하여 원본 값과 일치하는 option이 선택되게 한다
            options[indexMatchedName].selected = "selected";
            // Printing Color SELECT 옵션 복구 끝

            let itemOptionSingleSidePriceElement = row.querySelector("input[name='item-option-single-side-price']");
            let originalItemOptionSingleSidePrice = itemOptionSingleSidePriceElement.dataset.itemOptionSingleSidePrice;
            row.querySelector("input[name='item-option-single-side-price']").value = originalItemOptionSingleSidePrice;

            let itemOptionDoubleSidePriceElement = row.querySelector("input[name='item-option-double-side-price']");
            let originalItemOptionDoubleSidePrice = itemOptionDoubleSidePriceElement.dataset.itemOptionDoubleSidePrice;
            row.querySelector("input[name='item-option-double-side-price']").value = originalItemOptionDoubleSidePrice;
        }

        if (fullCategoryName === 'binding/binding-types' ||
            fullCategoryName === 'cover/coating-types' ||
            fullCategoryName === 'cover/design-types' ||
            fullCategoryName === 'flyleaf/flyleaf-color-types') {

            let itemOptionNameElement = row.querySelector("input[name='item-option-name']");
            let originalItemOptionName = itemOptionNameElement.dataset.itemOptionName;
            row.querySelector("input[name='item-option-name']").value = originalItemOptionName;
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

    if (fullCategoryName === 'flyleaf/flyleaf-content') {

        let itemOptionPrice = "item-option-price";
        let isPriceReadOnly = row.querySelector("input[name='" + itemOptionPrice + "']").getAttribute("readonly");

        if (isPriceReadOnly === null) {
            row.querySelector("input[name='" + itemOptionPrice + "']").setAttribute("readonly", "")
        } else {
            row.querySelector("input[name='" + itemOptionPrice + "']").removeAttribute("readonly")
        }
    }

    if(fullCategoryName === 'paper/paper-types' || fullCategoryName === 'paper/paper-size-types') {

        let itemOptionName = "item-option-name";
        let isNameReadOnly = row.querySelector("input[name='" + itemOptionName + "']").getAttribute("readonly");

        if (isNameReadOnly === null) {
            row.querySelector("input[name='" + itemOptionName + "']").setAttribute("readonly", "")
        } else {
            row.querySelector("input[name='" + itemOptionName + "']").removeAttribute("readonly")
        }
    }

    if(fullCategoryName === 'cover/paper-types' || fullCategoryName === 'content/paper-types') {

        let itemOptionName = "item-option-name";
        let isNameReadOnly = row.querySelector("select[name='" + itemOptionName + "']").getAttribute("disabled");
        let itemOptionSize = "item-option-size";
        let isSizeReadOnly = row.querySelector("select[name='" + itemOptionSize + "']").getAttribute("disabled");
        let itemOptionPrintingColor = "item-option-printing-color";
        let isPrintingColorReadOnly = row.querySelector("select[name='" + itemOptionPrintingColor + "']").getAttribute("disabled");
        let itemOptionSingleSidePrice = "item-option-single-side-price";
        let isSingleSideReadOnly = row.querySelector("input[name='" + itemOptionSingleSidePrice + "']").getAttribute("readonly");
        let itemOptionDoubleSidePrice = "item-option-double-side-price";
        let isDoubleSideReadOnly = row.querySelector("input[name='" + itemOptionDoubleSidePrice + "']").getAttribute("readonly");

        if (isNameReadOnly === null &&
            isSizeReadOnly === null &&
            isPrintingColorReadOnly === null &&
            isSingleSideReadOnly === null &&
            isDoubleSideReadOnly === null) {
            row.querySelector("select[name='" + itemOptionName + "']").setAttribute("disabled", "")
            row.querySelector("select[name='" + itemOptionSize + "']").setAttribute("disabled", "")
            row.querySelector("select[name='" + itemOptionPrintingColor + "']").setAttribute("disabled", "")
            row.querySelector("input[name='" + itemOptionSingleSidePrice + "']").setAttribute("readonly", "")
            row.querySelector("input[name='" + itemOptionDoubleSidePrice + "']").setAttribute("readonly", "")
        } else {
            row.querySelector("select[name='" + itemOptionName + "']").removeAttribute("disabled")
            row.querySelector("select[name='" + itemOptionSize + "']").removeAttribute("disabled")
            row.querySelector("select[name='" + itemOptionPrintingColor + "']").removeAttribute("disabled")
            row.querySelector("input[name='" + itemOptionSingleSidePrice + "']").removeAttribute("readonly")
            row.querySelector("input[name='" + itemOptionDoubleSidePrice + "']").removeAttribute("readonly")
        }
    }

    if (fullCategoryName === 'binding/binding-types' ||
        fullCategoryName === 'cover/coating-types' ||
        fullCategoryName === 'cover/design-types' ||
        fullCategoryName === 'flyleaf/flyleaf-color-types') {

        let itemOptionName = "item-option-name";
        let isNameReadOnly = row.querySelector("input[name='" + itemOptionName + "']").getAttribute("readonly");
        let itemOptionPrice = "item-option-price";
        let isPriceReadOnly = row.querySelector("input[name='" + itemOptionPrice + "']").getAttribute("readonly");

        if (isNameReadOnly === null && isPriceReadOnly === null) {
            row.querySelector("input[name='" + itemOptionName + "']").setAttribute("readonly", "")
            row.querySelector("input[name='" + itemOptionPrice + "']").setAttribute("readonly", "")
        } else {
            row.querySelector("input[name='" + itemOptionName + "']").removeAttribute("readonly")
            row.querySelector("input[name='" + itemOptionPrice + "']").removeAttribute("readonly")
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
    let price = 0;
    let size = "";
    let printingColor = "";
    let singleSidePrice = 0;
    let doubleSidePrice = 0;
    let itemOptionNameElement = null;
    let itemOptionPriceElement = null;
    let itemOptionSizeElement = null;
    let itemOptionPrintingColorElement = null;
    let itemOptionSingleSidePriceElement = null;
    let itemOptionDoubleSidePriceElement = null;
    let fullCategoryName = getFullCategoryName(row.parentElement)

    if (fullCategoryName === 'flyleaf/flyleaf-content') {

        price = data.price;
        itemOptionPriceElement = row.querySelector("input[name='item-option-price']");
        itemOptionPriceElement.value = singleSidePrice;
        itemOptionPriceElement.dataset.itemOptionPrice = price;
    }

    if (fullCategoryName === 'paper/paper-types' ||
        fullCategoryName === 'paper/paper-size-types') {

        itemOptionNameElement = row.querySelector("input[name='item-option-name']");
        itemOptionNameElement.value = name; // update text value
        row.dataset.itemOptionId = id; // update dataset
        itemOptionNameElement.dataset.itemOptionName = name;
    }

    if (fullCategoryName === 'cover/paper-types' ||
        fullCategoryName === 'content/paper-types') {

        itemOptionNameElement = row.querySelector("select[name='item-option-name']");
        itemOptionNameElement.value = name; // update text value
        row.dataset.itemOptionId = id; // update dataset
        itemOptionNameElement.dataset.itemOptionName = name;

        size = data.size;
        itemOptionSizeElement = row.querySelector("select[name='item-option-size']");
        itemOptionSizeElement.options[itemOptionSizeElement.selectedIndex].value = size;
        itemOptionSizeElement.dataset.itemOptionSize = size;

        printingColor = data.printingColor;
        itemOptionPrintingColorElement = row.querySelector("select[name='item-option-printing-color']");
        itemOptionPrintingColorElement.options[itemOptionPrintingColorElement.selectedIndex].value = printingColor;
        itemOptionPrintingColorElement.dataset.itemOptionPrintingColor = printingColor;

        singleSidePrice = data.singleSidePrice;
        itemOptionSingleSidePriceElement = row.querySelector("input[name='item-option-single-side-price']");
        itemOptionSingleSidePriceElement.value = singleSidePrice;
        itemOptionSingleSidePriceElement.dataset.itemOptionSingleSidePrice = singleSidePrice;

        doubleSidePrice = data.doubleSidePrice;
        itemOptionDoubleSidePriceElement = row.querySelector("input[name='item-option-double-side-price']");
        itemOptionDoubleSidePriceElement.value = doubleSidePrice;
        itemOptionDoubleSidePriceElement.dataset.itemOptionDoubleSidePrice = doubleSidePrice;
    }

    if (fullCategoryName === 'binding/binding-types' ||
        fullCategoryName === 'cover/coating-types' ||
        fullCategoryName === 'cover/design-types' ||
        fullCategoryName === 'flyleaf/flyleaf-color-types') {

        itemOptionNameElement = row.querySelector("input[name='item-option-name']");
        itemOptionNameElement.value = name; // update text value
        row.dataset.itemOptionId = id; // update dataset
        itemOptionNameElement.dataset.itemOptionName = name;

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
    let itemOptionName = "";
    let itemOptionPrice = 0;
    let itemOptionData = {};
    let itemOptionSize = "";
    let itemOptionPrintingColor = "";
    let itemOptionSingleSidePrice = 0;
    let itemOptionDoubleSidePrice = 0;

    if (fullCategoryName === 'paper/paper-types' || fullCategoryName === 'paper/paper-size-types') {

        itemOptionName = row.querySelector("input[name='item-option-name']").value;

        itemOptionData = {
            "itemId" : itemId,
            "name" : itemOptionName,
        };
    }

    if (fullCategoryName === 'cover/paper-types' || fullCategoryName === 'content/paper-types') {

        itemOptionName = row.querySelector("select[name='item-option-name']").value;
        itemOptionSize = row.querySelector("select[name='item-option-size']").value;
        itemOptionPrintingColor = row.querySelector("select[name='item-option-printing-color']").value;
        itemOptionSingleSidePrice = row.querySelector("input[name='item-option-single-side-price']").value;
        itemOptionDoubleSidePrice = row.querySelector("input[name='item-option-double-side-price']").value;

        if (!isNumber(itemOptionSingleSidePrice) || !isNumber(itemOptionDoubleSidePrice)) {
            return;
        }

        itemOptionData = {
            "itemId" : itemId,
            "category" : itemOptionCategoryName,
            "name" : itemOptionName,
            "size" : itemOptionSize,
            "printingColor" : itemOptionPrintingColor,
            "singleSidePrice" : itemOptionSingleSidePrice,
            "doubleSidePrice" : itemOptionDoubleSidePrice
        };
    }

    if (fullCategoryName === 'binding/binding-types' ||
        fullCategoryName === 'cover/coating-types' ||
        fullCategoryName === 'cover/design-types' ||
        fullCategoryName === 'flyleaf/flyleaf-color-types') {
        itemOptionName = row.querySelector("input[name='item-option-name']").value;
        itemOptionPrice = row.querySelector("input[name='item-option-price']").value;
        itemOptionData = {
            "itemId" : itemId,
            "name" : itemOptionName,
            "price" : itemOptionPrice
        };

        if (!isNumber(itemOptionPrice)) {
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

// 숫자만 입력을 허용한다
function isNumber(value) {
    let allowOnlyNumbersRegex = new RegExp(/^[0-9]*$/);
    if (value === "" || value === undefined || !allowOnlyNumbersRegex.test(value)) {
        alert("가격 항목은 숫자만 입력할 수 있습니다.");
        return false;
    }
    return true;
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
    let itemOptionName = null;
    let itemOptionPrice = null;
    let itemOptionSize = null;
    let itemOptionPrintingColor = null;
    let itemOptionSingleSidePrice = null;
    let itemOptionDoubleSidePrice = null;
    let itemOptionData = null;

    if (fullCategoryName === 'flyleaf/flyleaf-content') {

        itemOptionPrice = row.querySelector("input[name='item-option-price']").value;

        if (!isNumber(itemOptionPrice)) {
            return;
        }

        itemOptionData = {
            "itemId" : itemId,
            "id" : itemOptionId,
            "price" : itemOptionPrice
        }
    }

    if (fullCategoryName === 'paper/paper-types' || fullCategoryName === 'paper/paper-size-types') {

        itemOptionName = row.querySelector("input[name='item-option-name']").value;

        itemOptionData = {
            "itemId" : itemId,
            "id" : itemOptionId,
            "name" : itemOptionName,
        };
    }

    if (fullCategoryName === 'cover/paper-types' || fullCategoryName === 'content/paper-types') {

        let nameElement = row.querySelector("select[name='item-option-name']");
        itemOptionName = nameElement.options[nameElement.selectedIndex].value;
        let sizeElement = row.querySelector("select[name='item-option-size']");
        itemOptionSize = sizeElement.options[sizeElement.selectedIndex].value;
        let printingColorElement = row.querySelector("select[name='item-option-printing-color']");
        itemOptionPrintingColor = printingColorElement.options[printingColorElement.selectedIndex].value;
        itemOptionSingleSidePrice = row.querySelector("input[name='item-option-single-side-price']").value;
        itemOptionDoubleSidePrice = row.querySelector("input[name='item-option-double-side-price']").value;

        if (!isNumber(itemOptionSingleSidePrice) || !isNumber(itemOptionDoubleSidePrice)) {
            return;
        }

        itemOptionData = {
            "itemId" : itemId,
            "id" : itemOptionId,
            "category" : itemOptionCategoryName,
            "name" : itemOptionName,
            "size" : itemOptionSize,
            "printingColor" : itemOptionPrintingColor,
            "singleSidePrice" : itemOptionSingleSidePrice,
            "doubleSidePrice" : itemOptionDoubleSidePrice
        };
    }

    if (fullCategoryName === 'binding/binding-types' ||
        fullCategoryName === 'cover/coating-types' ||
        fullCategoryName === 'cover/design-types' ||
        fullCategoryName === 'flyleaf/flyleaf-color-types') {
        itemOptionName = row.querySelector("input[name='item-option-name']").value;
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
    let fullCategoryName = getFullCategoryName(row.parentElement);
    let itemOptionName = null;

    if (fullCategoryName === 'cover/paper-types' || fullCategoryName === 'content/paper-types') {
        let itemOptionNameElement = row.querySelector("select[name='item-option-name']")
        itemOptionName = itemOptionNameElement.options[itemOptionNameElement.selectedIndex].value;
    }

    if (fullCategoryName !== 'cover/paper-types' && fullCategoryName !== 'content/paper-types') {
        itemOptionName = row.querySelector("input[name='item-option-name']").value
    }

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
