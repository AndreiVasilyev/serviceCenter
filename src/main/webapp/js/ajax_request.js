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
        result = 'error';
    }
    return result;
}

//-----POST queries -----

//post-query with json body

async function sendPostJsonQuery(url, data) {
    let response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(data)
    });
    console.log('status=' + response.status);
    return response.ok ? await response.json() : 'error';
}

//post-query with form-data

async function sendPostFormQuery(url, data) {
    let response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: data
    });
    return response.ok ? await response.json() : 'error';
}