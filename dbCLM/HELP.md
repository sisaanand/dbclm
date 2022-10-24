# Getting Started

dbCLM application consists of 2 APIs. One to load naceDetails and other to fetch NACE details by requested Order ID.

Build/Run application Steps through CMD:
----------------------------------------
Build applicaton using Maven, through cmd - "mvn install" from project location  to execute all test cases and will build jar with snapshot version. 

To run application, go to project path in cmd and type "mvn spring-boot:run" to execute the application.


API details :
------------

1. getNaceDetails
   - Fetch NACE Detail for requested Order ID as JSON response.
   
 URI -  /clm/v1/getNaceDetails/{OrderID}
 
 Sample Request: /clm/v1/getNaceDetails/398481
 Sample Response:
 {
    "completed": true,
    "errorMsg": null,
    "violations": null,
    "data": {
        "Order": 398481,
        "Level": 1,
        "Code": "A",
        "Parent": "",
        "Description": "AGRICULTURE, FORESTRY AND FISHING",
        "This item includes": "This section includes the exploitation of vegetal and animal natural resources, comprising the activities of growing of crops, raising and breeding of animals, harvesting of timber and other plants, animals or animal products from a farm or their natural habitats.",
        "This item also includes": "",
        "Rulings": "",
        "This item excludes": "",
        "Reference to ISIC Rev. 4": "A"
    }
} 

2. naceDetails:
	- Take a list of Orders in json format in request and store to application DB. Responds data as success for valid scenario. 

URI -  clm/v1/naceDetails

Sample Request body:
{
    "naceDetailList":[
	{
	  "Order" : 3984820088888888888,
	  "Level" : 2,
	  "Code" : "1ABCDEFGH",
	  "Parent" : "A",
	  "Description" : "Crop and animal production, hunting and related service activities",
	  "This item includes" : "This division includes two basic activities, namely the production of crop products and production of animal products, covering also the forms of organic agriculture, the growing of genetically modified crops and the raising of genetically modified animals. This division includes growing of crops in open fields as well in greenhouses.\n \nGroup 01.5 (Mixed farming) breaks with the usual principles for identifying main activity. It accepts that many agricultural holdings have reasonably balanced crop and animal production, and that it would be arbitrary to classify them in one category or the other.",
	  "This item also includes" : "This division also includes service activities incidental to agriculture, as well as hunting, trapping and related activities.",
	  "Rulings" : "",
	  "This item excludes" : "Agricultural activities exclude any subsequent processing of the agricultural products (classified under divisions 10 and 11 (Manufacture of food products and beverages) and division 12 (Manufacture of tobacco products)), beyond that needed to prepare them for the primary markets. The preparation of products for the primary markets is included here.\n\nThe division excludes field construction (e.g. agricultural land terracing, drainage, preparing rice paddies etc.) classified in section F (Construction) and buyers and cooperative associations engaged in the marketing of farm products classified in section G. Also excluded is the landscape care and maintenance, which is classified in class 81.30.",
	  "Reference to ISIC Rev. 4" : "1"
	}
]} 	
	
Sample Response body:	
{
    "completed": true,
    "errorMsg": null,
    "violations": null,
    "data": "Success"
}

Swagger UI URL 
--------------
http://localhost:8080/swagger-ui/index.html#/clm-controller