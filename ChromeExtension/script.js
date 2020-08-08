chrome.tabs.executeScript({
    code: "document.getSelection().anchorNode.textContent"
}, function(selectText) {
    document.querySelector("#selectText").innerText = selectText[0]
})