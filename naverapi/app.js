const express = require('express');
const bodyParser = require('body-parser');
const axios = require('axios');  
const path = require('path');    
const app = express();
const PORT = process.env.PORT || 3001;

const client_id = 'NkNZjqUUTvOsXo9YpKYd';
const client_secret = 'DwxMJn57c2';

app.use(express.static(path.join(__dirname, 'public')));

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));


app.get('/', (req, res) => {
    const filePath = path.join(__dirname, 'public', 'predict.html');
    console.log('Attempting to send file from:', filePath);
    res.sendFile(filePath);
});

app.post('/api/searchTrends', (req, res) => {
    console.log(555);
    const api_url = 'https://openapi.naver.com/v1/datalab/search';
    const keywordGroupsInput = req.body.keywordGroups.map(group => ({
        groupName: group.groupName,
        keywords: group.keywords.map(keyword => keyword.trim())
    }));
    const request_body = {
        startDate: req.body.startDate,
        endDate: req.body.endDate,
        timeUnit: req.body.timeUnit,
        keywordGroups: keywordGroupsInput,
        device: req.body.device,
        ages: req.body.ages,
        gender: req.body.gender
    };

    console.log('Request body:', request_body);

    axios.post(api_url, request_body, {
        headers: {
            'X-Naver-Client-Id': client_id,
            'X-Naver-Client-Secret': client_secret,
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        console.log('Response data:', response.data);
        res.json(response.data);
    })
    .catch(error => {
        console.log('Error response:', error.response ? error.response.data : error.message);
        res.status(error.response ? error.response.status : 500).json(error.response ? error.response.data : { message: "Internal Server Error" });
    });
});

app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});

