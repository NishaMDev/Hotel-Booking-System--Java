
let master='http://ec2-35-167-145-126.us-west-2.compute.amazonaws.com:8080'

// let master='http://localhost:8080'

export const getCall=async (api,params) => {
    let query=''
if(params){
      query = Object.keys(params)
        .map(k => encodeURIComponent(k) + '=' + encodeURIComponent(params[k]))
        .join('&');
}
    let url=`${master}/api/${api}`
    if(params){
        url=url+'?'+query

    }
    console.log('tet55')

     const output= await fetch(url, {
        "headers": {
            "accept": "application/json",
            "accept-language": "en-US,en;q=0.9,hi;q=0.8",
            "sec-ch-ua": "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"101\", \"Google Chrome\";v=\"101\"",
            "sec-ch-ua-mobile": "?0",
            "sec-ch-ua-platform": "\"macOS\"",
            "sec-fetch-dest": "empty",
        },
        "referrerPolicy": "strict-origin-when-cross-origin",
        "body": null,
        "method": "GET",

    });
    return await output.json()
}


export const postCall=async (api,body) => {
    const output= await fetch(`${master}/api/${api}`, {
        "headers": {
            "accept": "*/*",
            "accept-language": "en-US,en;q=0.9,hi;q=0.8",
            "content-type": "application/json",
            "sec-ch-ua": "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"101\", \"Google Chrome\";v=\"101\"",
            "sec-ch-ua-mobile": "?0",
            "sec-ch-ua-platform": "\"macOS\"",
            "sec-fetch-dest": "empty",

        },
         "referrerPolicy": "strict-origin-when-cross-origin",
        "body":  body,
        "method": "POST",

    });
    return await output.json()
}


export const deleteCall=async (api) => {
      await fetch(`${master}/api/${api}`, {
        "headers": {
            "accept": "*/*",
            "accept-language": "en-US,en;q=0.9,hi;q=0.8",
            "content-type": "application/json",
            "sec-ch-ua": "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"101\", \"Google Chrome\";v=\"101\"",
            "sec-ch-ua-mobile": "?0",
            "sec-ch-ua-platform": "\"macOS\"",
            "sec-fetch-dest": "empty",

        },
        "referrerPolicy": "strict-origin-when-cross-origin",
        "method": "DELETE",

    });
}