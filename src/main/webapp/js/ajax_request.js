//-----GET queries -----

//simple get-query

async function sendGetStringQuery(url) {
    let result;
    let response;
    try {
        response = await fetch(url);
        if (!response.ok) {
            throw Error('Error sending query')
        }
        result = await response.text();
    } catch (error) {
        console.log('error when sending json query to server:' + url);
        console.log('response status:' + response.status)
        location.href = '/control?command=goto_error_page';
    }
    return result;
}

//-----POST queries -----

//post-query with json body

function sendPostJsonQuery(url, data) {
    let init = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(data),
        redirect: 'manual'
    }
    return sendQuery(url, init);
}

//post-query with form-data

function sendPostFormQuery(url, data) {
    let init = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: data
        , redirect: 'manual'
    }
    return sendQuery(url, init);
}

async function sendQuery(url, init) {
    let result;
    let response;
    try {
        response = await fetch(url, init);
        if (!response.ok) {
            console.log('response is not OK');
            console.log('response status:' + response.status);
            throw Error('Error sending query');
        }
        result = await response.json();
    } catch (error) {
        console.log('Error when sending json query to server:' + url);
        console.log('Error: ' + error);
        console.log('is redirected: ' + response.redirected);
        if (response != null && !response.redirected) {
            location.href = '/control?command=goto_error_page';
        }
    }
    return result;
}

