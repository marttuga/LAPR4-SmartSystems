/*var json = {
    "Warehouse": "Warehouse Plan",
    "Length": 2800,
    "Width": 2000,
    "Square": 100,
    "Unit": "cm",
    "Aisles":[
        {
            "Id":1,
            "begin": {"lsquare":5, "wsquare":1},
            "end": {"lsquare":16, "wsquare":1},
            "depth":{"lsquare":16, "wsquare":1},
            "accessibility":"w+",
            "rows":[
                {
                    "Id":1,
                    "begin": {"lsquare":5, "wsquare":1},
                    "end": {"lsquare":7, "wsquare":1},
                    "shelves":3
                },
                {
                    "Id":2,
                    "begin": {"lsquare":8, "wsquare":1},
                    "end": {"lsquare":12, "wsquare":1},
                    "shelves":4
                },
            ]
        },
        {
            "Id":2,
            "begin": {"lsquare":5, "wsquare":8},
            "end": {"lsquare":16, "wsquare":8},
            "depth":{"lsquare":16, "wsquare":9},
            "accessibility":"w-",
            "rows":[
                {
                    "Id":1,
                    "begin": {"lsquare":5, "wsquare":8},
                    "end": {"lsquare":9, "wsquare":8},
                    "shelves":4
                },
                {
                    "Id":2,
                    "begin": {"lsquare":10, "wsquare":8},
                    "end": {"lsquare":11, "wsquare":8},
                    "shelves":2
                },
                {
                    "Id":3,
                    "begin": {"lsquare":12, "wsquare":8},
                    "end": {"lsquare":13, "wsquare":8},
                    "shelves":4
                },
                {
                    "Id":4,
                    "begin": {"lsquare":14, "wsquare":8},
                    "end": {"lsquare":16, "wsquare":8},
                    "shelves":3
                }
            ]
        },
        {
            "Id":3,
            "begin": {"lsquare":5, "wsquare":11},
            "end": {"lsquare":16, "wsquare":11},
            "depth":{"lsquare":16, "wsquare":10},
            "accessibility":"w+",
            "rows":[
                {
                    "Id":1,
                    "begin": {"lsquare":5, "wsquare":11},
                    "end": {"lsquare":11, "wsquare":11},
                    "shelves":4
                },
            ]
        },
        {
            "Id":4,
            "begin": {"lsquare":5, "wsquare":18},
            "end": {"lsquare":16, "wsquare":18},
            "depth":{"lsquare":16, "wsquare":18},
            "accessibility":"w-",
            "rows":[
                {
                    "Id":1,
                    "begin": {"lsquare":5, "wsquare":18},
                    "end": {"lsquare":8, "wsquare":18},
                    "shelves":4
                },
                {
                    "Id":2,
                    "begin": {"lsquare":9, "wsquare":18},
                    "end": {"lsquare":12, "wsquare":18},
                    "shelves":6
                },
            ]
        }
    ],
    "AGVDocks":[
        {
            "Id":"D1",
            "begin": {"lsquare":1, "wsquare":3},
            "end": {"lsquare":1, "wsquare":3},
            "depth":{"lsquare":1, "wsquare":3},
            "accessibility":"l+"
        },
        {
            "Id":"D2",
            "begin": {"lsquare":1, "wsquare":5},
            "end": {"lsquare":1, "wsquare":5},
            "depth":{"lsquare":1, "wsquare":5},
            "accessibility":"l+"
        },
        {
            "Id":"D3",
            "begin": {"lsquare":1, "wsquare":13},
            "end": {"lsquare":1, "wsquare":13},
            "depth":{"lsquare":1, "wsquare":13},
            "accessibility":"l+"
        },
    ]
}*/


function getAGVStatus() {
    var request = new XMLHttpRequest();
    var vBoard=document.getElementById("agvStatus");


    request.onload = function() {
        vBoard.innerHTML = this.responseText;
        setTimeout(getAGVStatus, 2000);
    };
    request.ontimeout = function() {
        vBoard.innerHTML = "Server timeout, still trying ...";
        setTimeout(getAGVStatus, 100);
    };
    request.onerror = function() {
        vBoard.innerHTML = "No server reply, still trying ...";
        setTimeout(getAGVStatus, 5000);
    };
    request.open("GET", "/agvStatus", true);
    request.timeout = 5000;
    request.send();
}
