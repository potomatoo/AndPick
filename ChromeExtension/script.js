document.querySelector("#getBtn").addEventListener("click", function() {
    chrome.storage.sync.get(function(data) {
        if (data.postContent !== undefined) {
            document.querySelector("#selectText").innerText = data.postContent
        }
    })
})

document.querySelector("#clearBtn").addEventListener("click", function() {
    chrome.storage.sync.clear()
})

document.querySelector("#saveBtn").addEventListener("click", function() {
    chrome.tabs.executeScript({
        code: "document.getSelection().anchorNode.textContent"
    }, function(selectText) {                
        chrome.storage.sync.get(function(data) {
            if (data.postContent !== undefined) {
                const nowData = data.postContent
                chrome.storage.sync.set({
                    postContent: nowData + selectText[0]
                })
            }
            else{
                chrome.storage.sync.set({
                    postContent: selectText[0]
                })
            }
        })       
    });
})
