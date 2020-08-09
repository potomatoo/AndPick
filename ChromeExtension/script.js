document.querySelector("#getBtn").addEventListener("click", function() {
    chrome.storage.sync.get(function(data) {
        document.querySelector("#selectText").innerText = data.postContent
    })
})
    




document.querySelector("#saveBtn").addEventListener("click", function() {
    chrome.tabs.executeScript({
        code: "document.getSelection().anchorNode.textContent"
    }, function(selectText) {
        document.querySelector("#selectText").innerText = selectText[0]
        chrome.storage.sync.set({
            postContent: selectText[0]
        })
        window.open("http://i3b107.p.ssafy.io/accounts/login", "_blank")
    });
})
