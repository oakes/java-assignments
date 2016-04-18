## HTML vs JSON routes

Thus far, we've been creating old-fashioned websites with HTML that we generate server-side using a templating engine (Mustache). We need to learn about a second, more modern approach to web apps that enforces more of a separation between the front-end and back-end. Instead of returning HTML, we can make our GET routes just return data encoded as JSON. Then, the front-end can request the data via JavaScript and generate the appropriate HTML itself.

To reiterate, the old way is:

1. Initiate request from HTML (link or form)
2. Server returns HTML
3. Browser refreshes page

The new way is:

1. Initiate request from JavaScript (AJAX)
2. Server returns JSON
3. JavaScript parses data and injects it into the page
