//////////////////////////////////////////////////////////////
// Globals
//////////////////////////////////////////////////////////////

// Used during debug mode to update window size message
var _isDebug;

// Used to hold the id of the button that was clicked
var currentActionButtonId;

// Used to enable multiple AJAX calls on a page
var _allowMultipleAJAXCalls = false;

// Message displayed in the status bar during AJAX calls
var _pleaseWaitMessage = "Please Wait...";

// AJAX Messages (compatibility, session timeout, max request length, SQL timeout, etc.)
var _ajax_CompatibilityMessage = "The entire suite of Matrix web applications uses AJAX technology and your browser settings are configured to disable the component required by AJAX.  \n\nPlease contact the CEVA Helpdesk for more information regarding this issue.";
var _ajax_SessionTimeoutMessage = "Your session has expired, please login again.";
var _ajax_MaxRequestLengthMessage = "Your request exceeds the maximum allowed.  Please try limiting your criteria and re-try your request again.";
var _ajax_SQLTimeoutMessage = "Matrix is currently experiencing a large volume of traffic.  Please re-try your request again.";
var _ajax_ServerTimeoutMessage = "Your request exceeded the maximum allowed timeout period.  This can be caused by system issues and/or networking related problems.\n\nPlease note that the request may have completed and resubmitting the request may result in duplicate records.";

//////////////////////////////////////////////////////////////
// Redirect to home page on timeout (iframe, popup, etc.)
//////////////////////////////////////////////////////////////
function SiteTimeout( url )
{
    if ( parent.location.href != window.location.href )
    {
        // Clear the browser history trick
        var Backlen = history.length;
        history.go( -Backlen );

        window.open( url, '_top' );
    }
    else if ( window.opener )
    {
        // Clear the browser history trick
        var Backlen = window.opener.history.length;
        window.opener.history.go( -Backlen );

        window.opener.location = url;
        window.close();
    }
}

//////////////////////////////////////////////////////////////
// Function to set the clicked button for later use
//////////////////////////////////////////////////////////////
function Processing( obj )
{
    // Disable the buttons, set the clicked button to "Processing..." 
    // and continue to raise the button click event
    if ( obj != null && obj != undefined && obj.id != "" )
    {
        // Store off the button that is disabled
        currentActionButtonId = obj.id;

        // Disable the button (gets re-enabled by the EndRequestHandler or a normal postback)
        obj.disabled = true;

        // Make sure the focus is on the page not the next button
        window.focus();

        // Continue the event
        javascript: __doPostBack( obj.name, null );
    }
}

//////////////////////////////////////////////////////////////
// Worker function to disable all buttons on the page
//////////////////////////////////////////////////////////////
function DoDisableButtons()
{
    // Get the Buttons to disable
    var buttonCollection = document.body.getElementsByTagName( "BUTTON" );
    if ( buttonCollection != null )
    {
        for ( i = 0; i < buttonCollection.length; i++ )
        {
            buttonCollection[i].disabled = true;
        }
    }

    // Get the input - submit & button to disable
    var inputCollection = document.body.getElementsByTagName( "INPUT" );
    if ( inputCollection != null )
    {
        for ( i = 0; i < inputCollection.length; i++ )
        {
            if ( inputCollection[i].type.toUpperCase() == "SUBMIT" || inputCollection[i].type.toUpperCase() == "BUTTON" || inputCollection[i].type.toUpperCase() == "IMAGE" )
            {
                inputCollection[i].disabled = true;
            }
        }
    }
}

//////////////////////////////////////////////////////////////
// AJAXInitializeHandler Helper function 
//////////////////////////////////////////////////////////////
function InitializeRequest( sender, args )
{
    var prm = Sys.WebForms.PageRequestManager.getInstance();
    if ( prm.get_isInAsyncPostBack() )
    {
        // Get the current action button if it exists
        if ( currentActionButtonId != null )
        {
            var currentActionButton = document.getElementById( currentActionButtonId );
            currentActionButtonId = null;

            // If there was an error we may need to re-enable the button that caused the event
            if ( currentActionButton != null )
            {
                if ( currentActionButton.disabled )
                {
                    currentActionButton.disabled = false;
                }
            }
        }
        if ( !_allowMultipleAJAXCalls )
        {
            // Cancel the event
            args.set_cancel( true );
        }
    }
}

//////////////////////////////////////////////////////////////
// AJAXBeginRequestHandler Helper function 
//////////////////////////////////////////////////////////////
function BeginRequestHandler( sender, args )
{
    window.status = _pleaseWaitMessage;
    document.body.style.cursor = "wait";

    try
    {
        document.getElementById( 'imgUpdating' ).style.visibility = 'visible';
    }
    catch ( e )
    {
        try
        {
            parent.document.getElementById( 'imgUpdating' ).style.visibility = 'visible';
        }
        catch ( e ) { }
    }
}

//////////////////////////////////////////////////////////////
// AJAXEndRequestHandler Helper function 
//    will re-enable the button if a error happens
//////////////////////////////////////////////////////////////
function EndRequestHandler(sender, args)
{
    window.status = "";
    document.body.style.cursor = "default";

    try
    {
        document.getElementById('imgUpdating').style.visibility = 'hidden';
    }
    catch (e)
    {
        try
        {
            parent.document.getElementById('imgUpdating').style.visibility = 'hidden';
        }
        catch (e) { }
    }

    var currentActionButton;

    // Get the current action button if it exists
    if (currentActionButtonId != null)
    {
        currentActionButton = document.getElementById(currentActionButtonId);
        currentActionButtonId = null;
    }

    if (args && args.get_error() != undefined)
    {
        var message = args.get_error().message;
        var error = args.get_error();
        var response = "";

        // Make sure we have a response to look at
        if (args.get_response().get_responseAvailable())
        {
            args.get_response().get_responseData();
        }

        // Check for common error types
        if (response.indexOf("document.AUTOSUBMIT.submit();") != -1)
        {
            // Check to see if we are getting a Siteminder session timeout		
            // and if so, give the user a message & redirect to the home page
            alert(_ajax_SessionTimeoutMessage);
            args.set_errorHandled(true);
            top.location = "/default.aspx";
        }
        else if (error.message.indexOf("System.Web.HttpException: Maximum request length exceeded.") != -1)
        {
            // Check to see if the request exceeded the max length
            alert(_ajax_MaxRequestLengthMessage);
            args.set_errorHandled(true);
        }
        else if (error.message.indexOf("The timeout period elapsed prior to completion of the operation or the server is not responding.") != -1)
        {
            // Check to see if the request exceeded the max length
            alert(_ajax_SQLTimeoutMessage);
            args.set_errorHandled(true);
        }
        else if (error.name == "Sys.WebForms.PageRequestManagerTimeoutException")
        {

            alert(_ajax_ServerTimeoutMessage);
            args.set_errorHandled(true);
        }
        else
        {
            alert(error.message);
            args.set_errorHandled(true);
        }

        // If there was an error we may need to re-enable the button that caused the event
        if (currentActionButton != null)
        {
            if (currentActionButton.disabled)
            {
                currentActionButton.disabled = false;
            }
        }
    }

    // Set the focus back to the item that caused the event
    if (currentActionButton != null)
    {
        currentActionButton.setActive();
    }
}

//////////////////////////////////////////////////////////////
// Abort the AJAX call for the <ESC> key
//////////////////////////////////////////////////////////////
function AbortRequestHandler()
{
    var prm = Sys.WebForms.PageRequestManager.getInstance();
    if ( prm.get_isInAsyncPostBack() )
    {
        if ( currentActionButtonId != null )
        {
            currentActionButton = document.getElementById( currentActionButtonId );
            currentActionButton.disabled = false;
            currentActionButtonId = null;
        }
        prm.abortPostBack();
    }
}

//////////////////////////////////////////////////////////////
// Check for AJAX compatibility
//////////////////////////////////////////////////////////////
function CheckAJAXCompatibility()
{
    var xmlRequest, e;
    try
    {
        xmlRequest = new XMLHttpRequest();
    }
    catch ( e )
    {
        try
        {
            xmlRequest = new ActiveXObject( "Microsoft.XMLHTTP" );
        }
        catch ( e ) { }
    }
    // Check to see if they can use AJAX
    if ( xmlRequest == undefined || xmlRequest == null )
    {
        alert( _ajax_CompatibilityMessage );
    }
}

//////////////////////////////////////////////////////////////
// Open Popup Function
//////////////////////////////////////////////////////////////
function OpenPopup( url, name, width, height, toolbars, scrollbars, status, menubar, resizable )
{
    // Get the screen resolution so we can center the dialog box
    var nScreenWidth = window.screen.width;
    var nScreenHeight = window.screen.height;

    var nScreenLeft = window.screenLeft;
    var nScreenTop = window.screenTop;
    var nScreenOffset = 0;

    // Check to see if we are using a multi-monitors
    if ( nScreenLeft - nScreenWidth >= 0 )
    {
        nScreenOffset = nScreenWidth;
    }

    var sWindowsLocationText = "";

    // If we have the width/height create the centering info
    if ( nScreenWidth != "" && nScreenHeight != "" )
    {
        sWindowsLocationText = ",left=" + ( ( ( nScreenWidth - width ) / 2 ) + nScreenOffset );
        sWindowsLocationText += ",top=" + ( nScreenHeight - height ) / 2;  // Special to remove space for status bar
    }

    // if in debug mode, always make popups resizable and show the status bar
    if ( _isDebug == true )
    {
        status = "Yes";
        resizable = "Yes";
    }

    var parameters = "width=" + width + ",height=" + height + ",toolbars=" + toolbars + ",scrollbars=" + scrollbars + ",status=" + status + ",menubar=" + menubar + ",resizable=" + resizable + sWindowsLocationText;

    var myPopup = window.open( url, name, parameters );
    myPopup.focus();
    return myPopup;
}

//////////////////////////////////////////////////////////////
// Show the Page Message Div & IFrame
//////////////////////////////////////////////////////////////
function ShowPageMessage()
{
    var pageMessageDiv = document.getElementById( "PageMessageDiv" );
    var pageMessageiFrame = document.getElementById( "PageMessageIFrame" );
    if ( pageMessageDiv != null && pageMessageiFrame != null )
    {
        toggleDiv( "PageMessageDiv", 1 );
        toggleIFrame( "PageMessageIFrame", 1 );
    }
}

//////////////////////////////////////////////////////////////
// Set Hide Info Message	
//////////////////////////////////////////////////////////////
function HidePageMessage()
{
    toggleDiv( "PageMessageDiv", 0 );
    toggleIFrame( "PageMessageIFrame", 0 );
}

//////////////////////////////////////////////////////////////
// Hide/Show a Div
//////////////////////////////////////////////////////////////
function toggleDiv( DivID, State ) // 1 visible, 0 hidden
{
    var obj = document.getElementById( DivID );
    obj.style.visibility = State ? "visible" : "hidden";
}

//////////////////////////////////////////////////////////////
// Hide/Show a IFrame
//////////////////////////////////////////////////////////////
function toggleIFrame( FrameID, State ) // 1 visible, 0 hidden
{
    var obj = document.getElementById( FrameID );
    obj.style.visibility = State ? "visible" : "hidden";
}

//////////////////////////////////////////////////////////////
// Hide/Show a Table
//////////////////////////////////////////////////////////////
function toggleTable( TableID, State ) // 1 visible, 0 hidden
{
    var obj = document.getElementById( TableID );
    //alert(obj)
    if ( obj != null )
    {
        obj.style.display = State ? "inline" : "none";
    }
}

//////////////////////////////////////////////////////////////
// Set focus and select a element if it exists	
//////////////////////////////////////////////////////////////
function SetFocus( FormFieldName )
{
    var obj = document.getElementById( FormFieldName );
    if ( obj != null && obj != undefined )
    {
        obj.focus();
        if ( obj.options == null )
        {
            obj.select();
        }
    }
}

//////////////////////////////////////////////////////////////
// Checks all check boxes in the grid calling it.
//////////////////////////////////////////////////////////////
function toggleCheckBoxChecked( checkbox )
{
    parentObj = GetParentByType( checkbox, "TABLE" );

    // Get all input types from the parent container	
    var colInput = parentObj.all.tags( 'input' );

    // iterate all the check boxes on the page with
    // this group name and set their checked value accordingly.
    for ( i = 0; i < colInput.length; i++ )
    {
        var myType = colInput[i].type;
        if ( myType == 'checkbox' && colInput[i].disabled != true )
        {
            colInput[i].checked = checkbox.checked;
        }
    }
}

//////////////////////////////////////////////////////////////
// Checks all check boxes in the grid calling it.
//////////////////////////////////////////////////////////////
function ClickCheckBox( checkbox )
{
    if ( checkbox != null && checkbox.checked != null && checkbox.disabled != true )
    {
        checkbox.checked = !checkbox.checked;
    }
}

//////////////////////////////////////////////////////////////
// Set radio button state
//////////////////////////////////////////////////////////////
function SetRadioButtonCheckState( checkedObject )
{
    if ( checkedObject ) // If null or undefined, will evaluate to false. 
    {
        parentObj = GetParentByType( checkedObject, "TABLE" );
        if ( parentObj ) // If null or undefined, will evaluate to false. 
        {
            // Get all input types from the parent container	
            var colInput = parentObj.all.tags( 'input' );
            if ( colInput ) // If null or undefined, will evaluate to false. 
            {
                // iterate all the radio buttons on the page with
                // this group name and set their state to unchecked.
                for ( i = 0; i < colInput.length; i++ )
                {
                    var myType = colInput[i].type;
                    if ( myType == 'radio' )
                    {
                        colInput[i].checked = false;
                    }
                }
                // finally set the checked state of this radio button to checked.
                checkedObject.checked = true;
            }
        }
    }
}

// Function to get the parent object by a specific tag type
function GetParentByType( childObj, findTagType )
{
    var tempObj = childObj.parentNode;

    while ( tempObj ) // If null or undefined, will evaluate to false. 
    {
        if ( tempObj.tagName.toUpperCase() == findTagType.toUpperCase() )
        {
            break;
        }
        tempObj = tempObj.parentNode;
    }
    return tempObj;
}


//////////////////////////////////////////////////////////////
// Runs after browser resize 
//////////////////////////////////////////////////////////////
function PageResize()
{
    if ( _isDebug )
    {
        // Show the window size in the status bar when in debug
        window.status = document.body.clientWidth + " x " + document.body.clientHeight;
    }
}

//////////////////////////////////////////////////////////////
// Generically handles setting an event in javascript so that 
//		an enter key goes to a specific object
//////////////////////////////////////////////////////////////
function HandleDefaultAction( obj )
{
    if ( ( event.which && event.which == 13 ) || ( event.keyCode && event.keyCode == 13 ) )
    {
        document.getElementById( obj ).click();
        return false;
    }
    else
    {
        return true;
    }
}

//////////////////////////////////////////////////////////////
// Utilities used to scroll a div horizontally
//////////////////////////////////////////////////////////////      
var timerDown;
var timerUp;

function scrollDivLeft( id )
{
    clearTimeout( timerDown );
    document.getElementById( id ).scrollLeft -= 2;
    timerDown = setTimeout( "scrollDivLeft('" + id + "')", 10 );
}

function scrollDivRight( id )
{
    clearTimeout( timerUp );
    document.getElementById( id ).scrollLeft += 2;
    timerUp = setTimeout( "scrollDivRight('" + id + "')", 10 );
}

function stopScroll()
{
    clearTimeout( timerDown );
    clearTimeout( timerUp );
}

//////////////////////////////////////////////////////////////
// This function is used by Search\NewLocationSearch.ascx
//////////////////////////////////////////////////////////////
function OpenLocationSearchPopup(FormField, SearchType, AliasDisabled, EntityType, EntityTypeEnabled, CustomerId, ValidEntityTypes, ValidAltEntityTypes)
{
    var path = document.location.pathname;
    var test = path.split( "/" );
    var realpath = "Searching/LocationSearch.aspx";
    for ( i = 0; i < test.length; i++ )
    {
        realpath = "../" + realpath;
    }

    var AliasInput = eval( 'document.all.' + FormField + '_txtAlias' );
    var Alias = AliasInput.value;

    if ( SearchType != "" )
    {
        url = realpath + "?SearchType=" + SearchType + "&FormField=" + FormField + "&Alias=" + Alias + "&AliasDisabled=" + AliasDisabled + "&EntityType=" + EntityType + "&EntityTypeEnabled=" + EntityTypeEnabled + "&CustomerId=" + CustomerId + "&ValidEntityTypes=" + ValidEntityTypes;
    }

    // Adjust for less than 1024x768
    var nScreenHeight = window.screen.height;

    if ( nScreenHeight < 770 )
    {
        nScreenHeight = 520;
    }
    else
    {
        nScreenHeight = 675;
    }

    // Open Window
    OpenPopup( url, "locationsearch", 900, ( nScreenHeight + 32 ), "no", "no", "yes", "no" )
}

//////////////////////////////////////////////////////////////
// This function is used by Searching\EntitySearch.ascx
//////////////////////////////////////////////////////////////
function OpenEntitySearchPopup( FormField, SearchType, EntityDisabled, EntityType, EntityTypeEnabled, CustomerId )
{
    var EntityInput = eval( 'document.all.' + FormField + '_txtEntity' );
    var Entity = EntityInput.value;

    var path = document.location.pathname;
    var test = path.split( "/" );
    var realpath = "Searching/EntitySearch.aspx";
    for ( i = 0; i < test.length; i++ )
    {
        realpath = "../" + realpath;
    }

    if ( SearchType != "" )
    {
        url = realpath + "?SearchType=" + SearchType + "&FormField=" + FormField + "&Entity=" + Entity + "&EntityDisabled=" + EntityDisabled + "&EntityType=" + EntityType + "&EntityTypeEnabled=" + EntityTypeEnabled + "&CustomerId=" + CustomerId;
    }

    // Open Window
    OpenPopup( url, "EntitySearch", 750, 582, "no", "no", "yes", "no" )
}

function ServiceProvider_Details()
{

}

function ServiceProvider_Changed()
{

}

//////////////////////////////////////////////////////////////
// This function is used by Searching\ServiceProviderSearch.cs
//////////////////////////////////////////////////////////////
function OpenNewServiceProviderSearchPopup( FormField, SearchType, BusinessUnitID )
{
    var ServiceProviderCodeInput = eval( 'document.all.' + FormField + '_txtServiceProviderCode' );
    var ServiceProviderCode = ServiceProviderCodeInput.value;

    if ( ServiceProviderCode != "" )
    {
        url = "/Searching/ServiceProviderSearch.aspx" + "?SearchType=" + SearchType + "&FormField=" + FormField + "&ServiceProviderCode=" + ServiceProviderCode + "&BusinessUnitId=" + BusinessUnitID;
    }

    // Open Window
    OpenPopup( url, "ServiceProviderSearch", 750, 582, "no", "no", "yes", "no" )
}

//////////////////////////////////////////////////////////////
// Select the Search Code
//////////////////////////////////////////////////////////////
function SetSearch( code )
{
    // The values parameter is an array that contains
    // all the values to be used to populate the calling form fields.

    if ( window.opener && !window.opener.closed )
    {
        var txtSearchCode = eval( 'window.opener.document.all.' + formfield + "_txtServiceProviderCode" );
        txtSearchCode.value = code;
        window.close();
    }
}

//////////////////////////////////////////////////////////////
// Clear Values for Entity Search 
//////////////////////////////////////////////////////////////
function Entity_Changed( name )
{
    var txtbox = eval( 'document.all.' + name + '_txtEntity' );
    if ( !txtbox.readOnly )
    {
        var txtbox = eval( 'document.all.' + name + '_txtEntityId' );
        txtbox.value = '';
    }
}

//////////////////////////////////////////////////////////////
// Select the Entity
//////////////////////////////////////////////////////////////
function PickEntity( values, formfield )
{
    // The values parameter is an array that contains
    // all the values to be used to populate the calling form fields.
    var answers = eval( values );

    if ( window.opener && !window.opener.closed )
    {
        var xBox = eval( 'window.opener.document.all.' + formfield + "_txtEntity" );
        xBox.value = answers[0];

        var xBox2 = eval( 'window.opener.document.all.' + formfield + "_txtEntityId" );
        xBox2.value = answers[1];

        window.close();
    }
}

//////////////////////////////////////////////////////////////
// Select the service provider
//////////////////////////////////////////////////////////////
function PickServiceProvider( values, formfield )
{
    var spArray = eval( values );

    if ( window.opener && !window.opener.closed )
    {
        var txtSPName = eval( 'window.opener.document.all.' + formfield + "_txtServiceProviderName" );
        txtSPName.value = spArray[0];

        var txtSPCode = eval( 'window.opener.document.all.' + formfield + "_txtServiceProviderCode" );
        txtSPCode.value = spArray[1];

        var txtSPID = eval( 'window.opener.document.all.' + formfield + "_txtServiceProviderId" );
        txtSPID.value = spArray[2];

        window.close();
    }
}

//////////////////////////////////////////////////////////////
// Set the search result for the new PartSearch control
//////////////////////////////////////////////////////////////
function SetSearchResult( values, formfield, close )
{
    // The values parameter is an array that contains
    // all the values to be used to populate the calling form fields.
    var answers = eval( values );

    if ( window.opener && !window.opener.closed )
    {
        var xBox = eval( 'window.opener.document.all.' + formfield + "_txtSearchField" );
        if ( xBox != null )
        {
            xBox.value = answers[0];
        }

        var xBox2 = eval( 'window.opener.document.all.' + formfield + "_lblDescription" );
        if ( xBox2 != null )
        {
            xBox2.innerText = answers[1];
        }

        // This field is being used to store the PartId
        var xBox3 = eval( 'window.opener.document.all.' + formfield + "_txtHiddenControl1" );
        if ( xBox3 != null )
        {
            xBox3.value = answers[2];
        }

        if ( close )
        {
            window.close();
        }
    }
}

//////////////////////////////////////////////////////////////
// Used by the new Part Search Control to wipe the description 
// and ID when a user types a value into the search textbox
//////////////////////////////////////////////////////////////
function Search_Text_Changed( clientId )
{
    var name = document.getElementById( clientId.id + '_lblDescription' );
    if ( name != null )
    {
        name.innerText = '';
    }

    var name = document.getElementById( clientId.id + '_txtPartId' );
    if ( name != null )
    {
        alert( 'name.innerText= ' + name.innerText );
        name.innerText = '';
    }

    var name = document.getElementById( clientId.id + '_txtHiddenControl1' );
    if ( name != null )
    {
        name.innerText = '';
    }
}

//////////////////////////////////////////////////////////////
// Clear Values for Location Search 
//////////////////////////////////////////////////////////////
function Location_Changed( name )
{
    var txtbox = eval( 'document.all.' + name + '_txtAlias' );
    if ( !txtbox.readOnly )
    {
        var imgDetails = eval( 'document.all.' + name + '_Details' );
        imgDetails.alt = 'If there is an alias click to view details otherwise Address Details will be shown';

        var imgDetails = eval( 'document.all.' + name + '_Search' );
        imgDetails.alt = 'Click to Search for Location';

        var imgDetails = eval( 'document.all.' + name + '_Validate' );
        imgDetails.alt = 'Click to Validate Alias';

        var txtbox = eval( 'document.all.' + name + '_txtEntityId' );
        txtbox.value = '';

        var xBox = eval( 'document.all.' + name + '_txtADIName' );
        xBox.value = '';

        var xBox = eval( 'document.all.' + name + '_txtADIAdd1' );
        xBox.value = '';

        var xBox = eval( 'document.all.' + name + '_txtADIAdd2' );
        xBox.value = '';

        var xBox = eval( 'document.all.' + name + '_txtADICity' );
        xBox.value = '';

        var xBox = eval( 'document.all.' + name + '_txtADIState' );
        xBox.value = '';

        var xBox = eval( 'document.all.' + name + '_txtADIPostalCode' );
        xBox.value = '';

        var xBox = eval( 'document.all.' + name + '_txtADICountryCode' );
        xBox.value = '';

        var xBox = eval( 'document.all.' + name + '_txtADIContactName' );
        xBox.value = '';

        var xBox = eval( 'document.all.' + name + '_txtADIDayPhone' );
        xBox.value = '';

        var xBox = eval( 'document.all.' + name + '_txtADIEvePhone' );
        xBox.value = '';

        var xBox = eval( 'document.all.' + name + '_txtADIId' );
        xBox.value = '';

        var xBox = eval( 'document.all.' + name + '_txtEntityName' );
        xBox.value = '';

        var xBox = eval( 'document.all.' + name + '_Search' );
        xBox.style.cursor = "hand";
        xBox.disabled = false;

        var xBox = eval( 'document.all.' + name + '_Validate' );
        xBox.style.cursor = "hand";
        xBox.disabled = false;
    }
}

//////////////////////////////////////////////////////////////
// Show Location Details 
//////////////////////////////////////////////////////////////
function Location_Details( entityId )
{
    if ( entityId == "" )
    {
        return;
    }

    var path = document.location.pathname;
    var test = path.split( "/" );
    var realpath = "Searching/LocationDetails.aspx";
    for ( i = 0; i < test.length; i++ )
    {
        realpath = "../" + realpath;
    }

    var url = realpath + "?entityId=" + entityId;

    // jrp: expand popup width- was a little too small
    OpenPopup( url, "LocationDetails", 700, 450, "no", "no", "yes" );
}

//////////////////////////////////////////////////////////////
// Select the location
//////////////////////////////////////////////////////////////
function PickLocation( values, formfield )
{
    PickLocation( values, formfield, false );
}

function PickLocation( values, formfield, doPostBack )
{
    // The values parameter is an array that contains
    // all the values to be used to populate the calling form fields.
    var answers = eval( values );

    if ( window.opener && !window.opener.closed )
    {
        var xBox1 = eval( 'window.opener.document.all.' + formfield + "_txtEntityName" );
        xBox1.value = answers[0];

        var xImg = eval( 'window.opener.document.all.' + formfield + "_Details" );
        xImg.alt = "Click to View Details for " + answers[0];

        var xBox3 = eval( 'window.opener.document.all.' + formfield + "_txtAlias" );
        xBox3.select();
        xBox3.value = answers[1];

        var xBox4 = eval( 'window.opener.document.all.' + formfield + "_txtEntityId" );
        xBox4.value = answers[2];

        if ( doPostBack )
        {
            window.opener.document.forms[0].submit();
        }

        var control = eval( 'window.opener.document.all.' + formfield + "_btnPostBack" );
        if ( control != null )
        {
            control.click();
        }

        window.close();
    }
}

//////////////////////////////////////////////////////////////
// This function is used by _Controls\RouteSearch.cs
//////////////////////////////////////////////////////////////
function ShowRouteSearch( FormField )
{
    var path = document.location.pathname;
    var test = path.split( "/" );
    var realpath = "Searching/RouteSearchDlg.aspx";
    for ( i = 0; i < test.length; i++ )
    {
        realpath = "../" + realpath;
    }

    var url = realpath + "?FormField=" + FormField;

    OpenPopup( url, "routesearch", 550, 532, "no", "no", "yes", "no" )
}

function ShowRouteSearchWithOD( FormField, OriginRequired, Origin, DestinationRequired, Destination )
{

    var formVar = eval( 'document.all.' + FormField );

    if ( Origin == null || Origin == '' )
    {
        var OriginInput = ReturnLocationString( 'Origin', FormField, formVar );
    }
    else
    {
        var OriginInput = Origin;
    }

    if ( Destination == null || Destination == '' )
    {
        var DestinationInput = ReturnLocationString( 'Destination', FormField, formVar );
    }
    else
    {
        var DestinationInput = Destination;
    }

    var path = document.location.pathname;
    var test = path.split( "/" );
    var realpath = "Searching/RouteSearchDlg.aspx";
    for ( i = 0; i < test.length; i++ )
    {
        realpath = "../" + realpath;
    }

    //get the destination alias from the selected control
    var url = realpath + "?FormField=" + FormField + "&Origin=" + OriginInput + "&OriginRequired=" + OriginRequired + "&Destination=" + DestinationInput + "&DestinationRequired=" + DestinationRequired;

    OpenPopup( url, 'routesearch', 600, 532, "no", "no", "yes", "no" )
}


//////////////////////////////////////////////////////////////
// Set Return Values for Route Search 
//////////////////////////////////////////////////////////////
function ReturnRouteSearch( returnValues, formfield )
{
    // The values parameter is an array that contains
    // all the values to be used to populate the calling form fields.
    var answers = eval( returnValues );

    if ( window.opener && !window.opener.closed )
    {
        var xBox = eval( 'window.opener.document.all.' + formfield );
        xBox.value = answers[0];

        var xBox1 = eval( 'window.opener.document.all.' + formfield + 'Id' );
        xBox1.select();
        xBox1.value = answers[1];

        if ( 'window.opener.document.all.' + formfield + 'btnPostBack' != null )
        {
            var btn = eval( 'window.opener.document.all.' + formfield + 'btnPostBack' );
            btn.click();
        }

        window.close();

    }
}

//////////////////////////////////////////////////////////////
// Clear Values for Route Search 
//////////////////////////////////////////////////////////////
function Route_Changed( name )
{
    var txtbox = eval( 'document.all.' + name + '_txtId' );
    txtbox.value = '';

    var name = document.getElementById( name + '_lblValidation' );
    if ( name != null )
    {
        name.innerText = '';
    }
}

//////////////////////////////////////////////////////////////
// This function is used by _Controls\CarrierSearch.cs
//////////////////////////////////////////////////////////////
function ShowCarrierSearch( FormField, Text )
{
    var path = document.location.pathname;
    var test = path.split( "/" );
    var realpath = "Searching/CarrierSearch.aspx";
    for ( i = 0; i < test.length; i++ )
    {
        realpath = "../" + realpath;
    }

    var url = realpath + "?FormField=" + FormField + "&Text=" + Text;

    OpenPopup( url, "carriersearch", 600, 582, "no", "no", "yes", "no" )
}

//////////////////////////////////////////////////////////////
// This function is used by _Controls\CarrierSearch.cs
//////////////////////////////////////////////////////////////
function ShowCarrierDetails( FormField, Text )
{
    if ( Text == "" )
    {
        return;
    }

    var path = document.location.pathname;
    var test = path.split( "/" );
    var realpath = "Searching/CarrierDetails.aspx";
    for ( i = 0; i < test.length; i++ )
    {
        realpath = "../" + realpath;
    }

    var url = realpath + "?FormField=" + FormField + "&Text=" + Text;

    OpenPopup( url, "carrierdetails", 811, 417, "no", "yes", "yes", "no" )
}

//////////////////////////////////////////////////////////////
// Set Return Values for Carrier Search 
//////////////////////////////////////////////////////////////
function ReturnCarrierSearch( returnValues, formfield )
{
    // The values parameter is an array that contains
    // all the values to be used to populate the calling form fields.
    var answers = eval( returnValues );

    if ( window.opener && !window.opener.closed )
    {
        var xBox = eval( 'window.opener.document.all.' + formfield );
        xBox.value = answers[0];
        xBox.select();

        var xBox = eval( 'window.opener.document.all.' + formfield + 'SCAC' );
        xBox.value = answers[1];

        var xBox = eval( 'window.opener.document.all.' + formfield + 'Name' );
        xBox.value = answers[2];

        var xBox = eval( 'window.opener.document.all.' + formfield + 'Alias' );
        xBox.value = answers[3];

        window.close();
    }
}

//////////////////////////////////////////////////////////////
// Clear Values for Carrier Search 
//////////////////////////////////////////////////////////////
function Carrier_Changed( name )
{
    var txtbox = eval( 'document.all.' + name + 'SCAC' );
    txtbox.value = '';
    var txtbox = eval( 'document.all.' + name + 'Name' );
    txtbox.value = '';
    var txtbox = eval( 'document.all.' + name + 'Alias' );
    txtbox.value = '';
}

//////////////////////////////////////////////////////////////
// This function is used by _Controls\RouteSearch.cs
//////////////////////////////////////////////////////////////
function ShowRouteSearch( FormField )
{
    var path = document.location.pathname;
    var test = path.split( "/" );
    var realpath = "Searching/RouteSearchDlg.aspx";
    for ( i = 0; i < test.length; i++ )
    {
        realpath = "../" + realpath;
    }

    var url = realpath + "?FormField=" + FormField;

    OpenPopup( url, "routesearch", 550, 532, "no", "no", "yes", "no" )
}

function ShowRouteSearchWithOD( FormField, OriginRequired, Origin, DestinationRequired, Destination )
{

    var formVar = eval( 'document.all.' + FormField );

    if ( Origin == null || Origin == '' )
    {
        var OriginInput = ReturnLocationString( 'Origin', FormField, formVar );
    }
    else
    {
        var OriginInput = Origin;
    }

    if ( Destination == null || Destination == '' )
    {
        var DestinationInput = ReturnLocationString( 'Destination', FormField, formVar );
    }
    else
    {
        var DestinationInput = Destination;
    }

    var path = document.location.pathname;
    var test = path.split( "/" );
    var realpath = "Searching/RouteSearchDlg.aspx";
    for ( i = 0; i < test.length; i++ )
    {
        realpath = "../" + realpath;
    }

    //get the destination alias from the selected control
    var url = realpath + "?FormField=" + FormField + "&Origin=" + OriginInput + "&OriginRequired=" + OriginRequired + "&Destination=" + DestinationInput + "&DestinationRequired=" + DestinationRequired;

    OpenPopup( url, 'routesearch', 600, 532, "no", "no", "yes", "no" )
}

//////////////////////////////////////////////////////////////
// This function is used by Search\LocationSearch.ascx
//////////////////////////////////////////////////////////////
function OpenUserDataPopup( FormName, width, height, url, SessionLoc, AppType )
{
    url = url + "?FormName=" + FormName + "&SessionLoc=" + SessionLoc + "&AppType=" + AppType;

    // Get the screen resolution so we can center the dialog box	
    var nScreenWidth = window.screen.width;
    var nScreenHeight = window.screen.height;
    var sWindowsLocationText = "";

    // If we have the width/height create the centering info
    if ( nScreenWidth != "" && nScreenHeight != "" )
    {
        sWindowsLocationText = ",left=" + ( nScreenWidth - width ) / 2;
        sWindowsLocationText += ",top=" + ( nScreenHeight - height ) / 2;
    }

    // Open Window
    curVar = OpenPopup( url, 'user_data', width, height, "no", "no", "yes", "no" )

    if ( !curVar.opener )
    {
        curVar.opener = self;
    }
}

//////////////////////////////////////////////////////////////
// Set Return Values for Parts Search 
//////////////////////////////////////////////////////////////
function ReturnUserData( formName )
{
    if ( window.opener && !window.opener.closed )
    {
        eval( 'window.opener.document.' + formName + '.submit();' );
        window.close();
    }
}

//////////////////////////////////////////////////////////////
// This function is used by Search\ContainersSearch.ascx
//////////////////////////////////////////////////////////////
function OpenContainersSearchPopup( FormField, width, height, url, CustomerId )
{

    var ContainerNumberInput = eval( 'document.all.' + FormField + '_txtContainerNumber' );
    var Container = ContainerNumberInput.value;

    var formVar = eval( 'document.all.' + FormField );

    //get the destination alias from the selected control
    url = url + "?FormField=" + FormField + "&Container=" + Container + "&CustomerId=" + CustomerId;

    // Open Window
    curVar = OpenPopup( url, 'container_search', width, height, "no", "no", "yes", "no" )
}

//////////////////////////////////////////////////////////////
// Set Return Values for Container Search 
//////////////////////////////////////////////////////////////
function ReturnContainerSearch( values, formfield )
{
    // The values parameter is an array that contains
    // all the values to be used to populate the calling form fields.
    var answers = eval( values );

    if ( window.opener && !window.opener.closed )
    {
        var xBox = eval( 'window.opener.document.all.' + formfield + '_txtContainerID' );
        xBox.select();
        xBox.value = answers[0];
        var xBox = eval( 'window.opener.document.all.' + formfield + '_txtContainerNumber' );
        xBox.value = answers[1];

        var xBox = eval( 'window.opener.document.all.' + formfield + '_btnPostBack' );
        if ( xBox != null )
        {
            xBox.click();
        }

        window.close();
    }
}

//////////////////////////////////////////////////////////////
// Used by the Container Search Control to wipe the description 
// and ID when a user types a value in
//////////////////////////////////////////////////////////////
function Container_Changed( container )
{
    var name = document.getElementById( container.id + '_txtContainerID' );
    name.value = '';

}


//////////////////////////////////////////////////////////////
// This function is used by _Controls\PowerUnitSearch.cs
//////////////////////////////////////////////////////////////
function ShowPowerUnitSearch( formControl, Text, RefreshBtn, GridItemIndex )
{
    var path = document.location.pathname;
    var test = path.split( "/" );
    var realpath = "Searching/PowerUnitSearch.aspx";
    for ( i = 0; i < test.length; i++ )
    {
        realpath = "../" + realpath;
    }
    var url = realpath + "?FormField=" + formControl + "&Text=" + Text + "&RefreshBtn=" + RefreshBtn + "&GridItemIndex=" + GridItemIndex;

    OpenPopup( url, "powerunitsearch", 600, 582, "no", "no", "yes", "no" )
}

//////////////////////////////////////////////////////////////
// Set Return Values for Power Unit Search 
//////////////////////////////////////////////////////////////
function ReturnPowerUnitSearch( returnValues, formControl )
{
    // The values parameter is an array that contains
    // all the values to be used to populate the calling form fields.
    var answers = eval( returnValues );

    if ( window.opener && !window.opener.closed )
    {
        var xBox = eval( 'window.opener.document.all.' + formControl + '_txtDisplay' );
        xBox.value = answers[0];
        xBox.select();

        var xBox = eval( 'window.opener.document.all.' + formControl + '_txtSCAC' );
        xBox.value = answers[1];

        var xBox = eval( 'window.opener.document.all.' + formControl + '_txtSCACDisplay' );
        xBox.value = answers[1];

        var xBox = eval( 'window.opener.document.all.' + formControl + '_txtId' );
        xBox.value = answers[3];

        try
        {
            window.opener.SetPowerUnitSCAC( answers[1] );
        }
        catch ( e ) { }

        window.close();
    }
}

//////////////////////////////////////////////////////////////
// Clear Values for Power Unit Search 
//////////////////////////////////////////////////////////////
function PowerUnit_Changed( formControl )
{
    var txtbox = eval( 'document.all.' + formControl + '_txtSCACDisplay' );
    txtbox.innerText = '';
    var xBox = eval( 'document.all.' + formControl + '_txtId' );
    xBox.value = '';
}

//////////////////////////////////////////////////////////////
// This function is used by _Controls\PowerUnitSearch.cs
//////////////////////////////////////////////////////////////
function ShowConveyanceSearch( formControl, Text, RefreshBtn, GridItemIndex )
{
    var path = document.location.pathname;
    var test = path.split( "/" );
    var realpath = "Searching/ConveyanceSearch.aspx";
    for ( i = 0; i < test.length; i++ )
    {
        realpath = "../" + realpath;
    }

    var url = realpath + "?FormField=" + formControl + "&Text=" + Text + "&RefreshBtn=" + RefreshBtn + "&GridItemIndex=" + GridItemIndex;

    OpenPopup( url, "conveyancesearch", 600, 582, "no", "no", "yes", "no" )
}

//////////////////////////////////////////////////////////////
// Set Return Values for Power Unit Search 
//////////////////////////////////////////////////////////////
function ReturnConveyanceSearch( returnValues, formControl )
{
    // The values parameter is an array that contains
    // all the values to be used to populate the calling form fields.
    var answers = eval( returnValues );


    if ( window.opener && !window.opener.closed )
    {


        var xBox = eval( 'window.opener.document.all.' + formControl + '_txtDisplay' );
        xBox.value = answers[0];
        xBox.select();

        var xBox = eval( 'window.opener.document.all.' + formControl + '_txtSCAC' );
        xBox.value = answers[1];

        var lbl = eval( 'window.opener.document.all.' + formControl + '_txtSCACDisplay' );
        xBox.value = answers[1];

        var xBox = eval( 'window.opener.document.all.' + formControl + '_txtId' );
        xBox.value = answers[3];

        var xBox = eval( 'window.opener.document.all.' + formControl + '_txtTypeId' );
        xBox.value = answers[4];

        var xBox = eval( 'window.opener.document.all.' + formControl + '_txtTypeDesc' );
        xBox.value = answers[5];

        window.close();
    }

}

//////////////////////////////////////////////////////////////
// Clear lbl for Conveyance Search 
//////////////////////////////////////////////////////////////
function Conveyance_Changed( formControl )
{
    var txtbox = eval( 'document.all.' + formControl + '_txtSCACDisplay' );
    txtbox.value = '';
    var xBox = eval( 'document.all.' + formControl + '_txtId' );
    xBox.value = '';
    var xBox = eval( 'document.all.' + formControl + '_txtTypeId' );
    xBox.value = '';
    var xBox = eval( 'document.all.' + formControl + '_txtTypeDesc' );
    xBox.value = '';
    var xBox = eval( 'document.all.' + formControl + '_txtSCAC' );
    xBox.value = '';
}

//////////////////////////////////////////////////////////////
// This function is used by Search\PartsSearch.ascx
//////////////////////////////////////////////////////////////
function OpenPartsSearchPopup( FormField, OriginRequired, Origin, DestinationRequired, Destination, CustomerId, width, height, url )
{
    var PartNumberInput = eval( 'document.all.' + FormField + '_txtPartNumber' );
    var Part = PartNumberInput.value;

    var formVar = eval( 'document.all.' + FormField );
    //get the origin alias from the selected control
    if ( Origin == null || Origin == '' )
    {
        var OriginInput = ReturnLocationString( 'Origin', FormField, formVar );
    }
    else
    {
        var OriginInput = Origin;
    }
    if ( Destination == null || Destination == '' )
    {
        var DestinationInput = ReturnLocationString( 'Destination', FormField, formVar );
    }
    else
    {
        var DestinationInput = Destination;
    }

    var path = document.location.pathname;
    var test = path.split( "/" );
    var realpath = "";
    for ( i = 0; i < test.length; i++ )
    {
        realpath = "../" + realpath;
    }

    //get the destination alias from the selected control
    url = realpath + url + "?FormField=" + FormField + "&Part=" + Part + "&Origin=" + OriginInput + "&OriginRequired=" + OriginRequired + "&Destination=" + DestinationInput + "&DestinationRequired=" + DestinationRequired + "&CustomerId=" + CustomerId;

    // Open Window
    curVar = OpenPopup( url, 'part_search', width, height, "no", "no", "yes", "no" )
}


//////////////////////////////////////////////////////////////
// This function is used as a utility by OpenPartsSearchPopup
//////////////////////////////////////////////////////////////
function ReturnLocationString( ControlName, FormField, formVar )
{
    var Input = formVar.getAttribute( ControlName + "ControlName", true );
    if ( Input != null )
    {
        var Input = eval( 'document.all.' + Input + '_txtAlias' );
    }
    else
    {
        var Input = formVar.getAttribute( ControlName, true );
    }
    if ( Input == null )
    {
        var Input = "";
    }
    else
    {
        var Input = Input.value;
    }
    return Input;
}

//////////////////////////////////////////////////////////////
// Set Return Values for Parts Search 
//////////////////////////////////////////////////////////////
function ReturnPartSearch( values, formfield )
{
    // The values parameter is an array that contains
    // all the values to be used to populate the calling form fields.
    var answers = eval( values );

    if ( window.opener && !window.opener.closed )
    {
        var xBox = eval( 'window.opener.document.all.' + formfield + '_txtPartNumber' );
        xBox.select();
        xBox.value = answers[0];
        var xBox = eval( 'window.opener.document.all.' + formfield + '_txtPartDescription' );
        xBox.value = answers[1];
        var xBox = eval( 'window.opener.document.all.' + formfield + '_txtPartPackagingID' );
        xBox.value = answers[2];

        window.close();
    }
}

//////////////////////////////////////////////////////////////
// Used by the Part Search Control to wipe the description 
// and ID when a user types a value in
//////////////////////////////////////////////////////////////
function Part_Changed( clientId )
{
    var name = document.getElementById( clientId.id + '_txtPartDescription' );
    name.value = '';
    var name = document.getElementById( clientId.id + '_txtPartPackagingID' );
    name.value = '';
}

function Part_Validate( clientId )
{
    var name = document.getElementById( clientId.id + '_imgValidate' );
    name.click();
}

//////////////////////////////////////////////////////////////////////////////////////////////
// This function is used by _Controls\LocationSearch.ascx to open the Address Details popup
//////////////////////////////////////////////////////////////////////////////////////////////
function OpenAddressDetailsPopup( FormField, CustomerId, ADIId, ADIName, Add1, Add2, City, District, State, PostalCode, CountryCode, ContactName, DayPhone, EvePhone, PageName, ReadOnly )
{

    // Escape single quotes
    ADIName = unescape( ADIName );
    ADIName = ADIName.replace( /'/g, "%039;" );
    Add1 = unescape( Add1 );
    Add1 = Add1.replace( /'/g, "%039;" );
    Add2 = unescape( Add2 );
    Add2 = Add2.replace( /'/g, "%039;" );
    City = unescape( City );
    City = City.replace( /'/g, "%039;" );
    District = unescape( District );
    District = District.replace( /'/g, "%039;" );
    ContactName = unescape( ContactName );
    ContactName = ContactName.replace( /'/g, "%039;" );
    ReadOnly = unescape( ReadOnly );
    ReadOnly = ReadOnly.replace( /'/g, "%039;" );

    var path = document.location.pathname;
    var test = path.split( "/" );
    var realpath = "Searching/AddressDetails.aspx";
    for ( i = 0; i < test.length; i++ )
    {
        realpath = "../" + realpath;
    }

    url = realpath + "?CustomerId=" + CustomerId + "&ADIId=" + ADIId + "&FormField=" + FormField + "&ADIName=" + ADIName + "&Add1=" + Add1 + "&Add2=" + Add2 + "&City=" + City + "&District=" + District + "&State=" + State + "&PostalCode=" + PostalCode + "&CountryCode=" + CountryCode + "&ContactName=" + ContactName + "&DayPhone=" + DayPhone + "&EvePhone=" + EvePhone + "&PageName=" + PageName + "&ReadOnly=" + ReadOnly;

    // Open Window
    OpenPopup( url, "addressdetails", 1070, 510, "no", "no", "yes", "no" )
}

//////////////////////////////////////////////////////////////
// Set Address Values for Location Search 
//////////////////////////////////////////////////////////////
function ReturnLocationSearch( returnValues, formfield )
{
    // The values parameter is an array that contains
    // all the values to be used to populate the calling form fields.
    var answers = eval( returnValues );

    if ( window.opener && !window.opener.closed )
    {
        var xBox = eval( 'window.opener.document.all.' + formfield );
        xBox.value = answers[0];

        var xBox = eval( 'window.opener.document.all.' + formfield + '_txtADIName' );
        xBox.value = answers[0];

        var xBox = eval( 'window.opener.document.all.' + formfield + '_txtADIAdd1' );
        xBox.value = answers[1];

        var xBox = eval( 'window.opener.document.all.' + formfield + '_Details' );
        xBox.alt = 'Click to view details for ' + unescape( answers[0] ) + ', ' + unescape( answers[1] );

        var xBox = eval( 'window.opener.document.all.' + formfield + '_txtADIAdd2' );
        xBox.value = answers[2];

        var xBox = eval( 'window.opener.document.all.' + formfield + '_txtADICity' );
        xBox.value = answers[3];

        var xBox = eval( 'window.opener.document.all.' + formfield + '_txtADIDistrict' );
        xBox.value = answers[4];

        var xBox = eval( 'window.opener.document.all.' + formfield + '_txtADIState' );
        xBox.value = answers[5];

        var xBox = eval( 'window.opener.document.all.' + formfield + '_txtADIPostalCode' );
        xBox.value = answers[6];

        var xBox = eval( 'window.opener.document.all.' + formfield + '_txtADICountryCode' );
        xBox.value = answers[7];

        var xBox = eval( 'window.opener.document.all.' + formfield + '_txtADIContactName' );
        xBox.value = answers[8];

        var xBox = eval( 'window.opener.document.all.' + formfield + '_txtADIDayPhone' );
        xBox.value = answers[9];

        var xBox = eval( 'window.opener.document.all.' + formfield + '_txtADIEvePhone' );
        xBox.value = answers[10];

        var xBox = eval( 'window.opener.document.all.' + formfield + '_txtADIId' );
        xBox.value = answers[11];

        var xBox = eval( 'window.opener.document.all.' + formfield + '_txtAlias' );
        xBox.value = answers[12];

        var xBox = eval( 'window.opener.document.all.' + formfield + '_txtEntityId' );
        xBox.value = answers[13];

        var xBox = eval( 'window.opener.document.all.' + formfield + '_txtEntityName' );
        xBox.value = unescape( answers[0] ) + ", " + unescape( answers[1] );

        var xBox = eval( 'window.opener.document.all.' + formfield + '_Validate' );
        xBox.style.cursor = "default";
        xBox.disabled = true;
        xBox.alt = "";

        var xBox = eval( 'window.opener.document.all.' + formfield + '_Search' );
        xBox.style.cursor = "default";
        xBox.disabled = true;
        xBox.alt = "";

        window.close();
    }
}
//////////////////////////////////////////////////////////////
// Generically handles refreshing a opening page and 
//		closing the popup
//////////////////////////////////////////////////////////////
function ReturnAndClose()
{
    if ( window.opener && !window.opener.closed )
    {
        window.opener.document.forms[0].submit();
    }
    window.close();
}
//////////////////////////////////////////////////////////////
// Generically handles refreshing a opening page but NOT 
//		closing the popup
//////////////////////////////////////////////////////////////
function Return()
{
    if ( window.opener && !window.opener.closed )
    {
        window.opener.document.forms[0].submit();
    }
}
//////////////////////////////////////////////////////////////
// This function is used by _Controls\DriverSearch.cs
//////////////////////////////////////////////////////////////
function ShowDriverSearch( FormField, DriverId, DriverNameClient, DriverPhoneClient )
{
    var path = document.location.pathname;
    var test = path.split( "/" );
    var realpath = "Searching/DriverSearch.aspx";
    for ( i = 0; i < test.length; i++ )
    {
        realpath = "../" + realpath;
    }

    var url = realpath + "?FormField=" + FormField + "&DriverId=" + DriverId + "&DriverNameClient=" + DriverNameClient + "&DriverPhoneClient=" + DriverPhoneClient;

    OpenPopup( url, "driversearch", 687, 607, "no", "no", "yes", "no" )
}
//////////////////////////////////////////////////////////////
// This function is used by _Controls\PersonnelSearch.cs
//////////////////////////////////////////////////////////////
function ShowPersonnelSearch( FormControl, PersonnelId, RefreshBtn, ServiceProviderCode, ServiceProviderCodeTypeId, UniqueCodeQualifier, GridItemIndex )
{
    var path = document.location.pathname;
    var test = path.split( "/" );
    var realpath = "Searching/PersonnelSearch.aspx";
    for ( i = 0; i < test.length; i++ )
    {
        realpath = "../" + realpath;
    }

    var url = realpath + "?FormField=" + FormControl + "&PersonnelId=" + PersonnelId + "&RefreshBtn=" + RefreshBtn + "&ServiceProviderCode=" + ServiceProviderCode + "&ServiceProviderCodeTypeId=" + ServiceProviderCodeTypeId + "&UniqueCodeQualifier=" + UniqueCodeQualifier + "&GridItemIndex=" + GridItemIndex;

    OpenPopup( url, "PersonnelSearch", 687, 666, "no", "no", "yes", "no" )
}
//////////////////////////////////////////////////////////////
// Set Return Values for Driver Search 
//////////////////////////////////////////////////////////////
function ReturnDriverSearch( controlName, driverId, driverNameClient, driverNameValue, driverContactClient, driverContactValue )
{
    // The values parameter is an array that contains
    // all the values to be used to populate the calling form fields.
    if ( window.opener && !window.opener.closed )
    {
        var xBox = eval( 'window.opener.document.all.' + controlName + '_txtDriverId' );
        xBox.value = driverId;

        var xBox1 = eval( 'window.opener.document.all.' + controlName + '_txtDriverName' );
        xBox1.value = driverNameValue;

        var xBox2 = eval( 'window.opener.document.all.' + controlName + '_txtDriverPhone' );
        xBox2.value = driverContactValue;

        if (driverNameClient != null && driverNameClient != '') 
        {
            var xBox3 = eval('window.opener.document.all.' + driverNameClient);
            xBox3.value = driverNameValue;
        }        

        if (driverContactClient != null && driverContactClient != '')
        {
            var xBox4 = eval( 'window.opener.document.all.' + driverContactClient );
            xBox4.value = driverContactValue;
        }
    }
    window.close();
}
//////////////////////////////////////////////////////////////
// Set Return Values for Personnel Search 
//////////////////////////////////////////////////////////////
function ReturnPersonnelSearch( controlName, personnelId, personnelName )
{
    // The values parameter is an array that contains
    // all the values to be used to populate the calling form fields.
    if ( window.opener && !window.opener.closed )
    {
        var txtbox = eval( 'window.opener.document.all.' + controlName + '_txtPersonnelId' );
        txtbox.value = personnelId;

        var lbl = eval( 'window.opener.document.all.' + controlName + '_lblName' );
        lbl.innerText = personnelName;

        var xBox = eval( 'window.opener.document.all.' + controlName + '_txtName' );
        xBox.value = personnelName;
    }
    window.close();
}
//////////////////////////////////////////////////////////////
// Change the Values for Driver Search 
//////////////////////////////////////////////////////////////
function Driver_Changed( controlName, driverNameClient, driverNameValue, driverContactClient, driverContactValue )
{
    var xBox = eval( 'document.all.' + controlName + '_txtDriverName' );
    xBox.value = driverNameValue;

    var xBox1 = eval( 'document.all.' + controlName + '_txtDriverPhone' );
    xBox1.value = driverContactValue;

    if (driverNameClient != null && driverNameClient != '') 
    {
        var xBox2 = eval('document.all.' + driverNameClient);
        xBox2.value = driverNameValue;
    }    

    if (driverContactClient != null && driverContactClient != '')
    {
        var xBox3 = eval( 'document.all.' + driverContactClient );
        xBox3.value = driverContactValue;
    }
}
//////////////////////////////////////////////////////////////
// Change the Values for Personnel Search 
//////////////////////////////////////////////////////////////
function Personnel_Changed( controlName )
{
    var lbl = eval( 'document.all.' + controlName + '_lblName' );
    lbl.innerText = '';
}
//////////////////////////////////////////////////////////////
// Cookie methods used for Confirm logic to track dirty flags 
//////////////////////////////////////////////////////////////
function GetCookie( name )
{
    var arg = name + '=';
    var alen = arg.length;
    var clen = document.cookie.length;
    var i = 0;
    while ( i < clen )
    {
        var j = i + alen;
        if ( document.cookie.substring( i, j ) == arg )
        {
            return getCookieVal( j );
        }
        i = document.cookie.indexOf( ' ', i ) + 1;
        if ( i == 0 ) break;
    }
    return null;
}
function getCookieVal( offset )
{
    var endstr = document.cookie.indexOf( ';', offset );
    if ( endstr == -1 )
    {
        endstr = document.cookie.length;
    }
    return unescape( document.cookie.substring( offset, endstr ) );
}
function SetCookie( name, value )
{
    SetCookie( name, value, Date(),
        window.document.location,
        window.document.domain,
        null )
}
function SetCookie( name, value, expires, path, domain, secure )
{
    document.cookie = name + '=' + escape( value ) +
        ( ( expires ) ? '; expires=' + expires.toGMTString() : '' ) +
        ( ( path ) ? '; path=' + path : '' ) +
        ( ( domain ) ? '; domain=' + domain : '' ) +
        ( ( secure ) ? '; secure' + secure : '' );
}
//////////////////////////////////////////////////////////////
// Confirm navigate away methods
//////////////////////////////////////////////////////////////
function InitConfirm( msg )
{
    SetCookie( window.document.nameProp + '.ConfirmMsg', msg );
    SetCookie( window.document.nameProp + '.IsDirty', false );
    SetCookie( window.document.nameProp + '.ShowConfirm', false );
}
function SetConfirmOn()
{
    var msg = GetCookie( window.document.nameProp + '.ConfirmMsg' );
    if ( msg != null && msg != '' )
    {
        window.onbeforeunload = ConfirmExit;
        SetCookie( window.document.nameProp + '.IsDirty', true );
        SetCookie( window.document.nameProp + '.ShowConfirm', true );
    }
}
function SetConfirmOff()
{
    window.onbeforeunload = '';
    SetCookie( window.document.nameProp + '.IsDirty', false );
    SetCookie( window.document.nameProp + '.ShowConfirm', false );
}
function SuppressConfirm()
{
    window.onbeforeunload = '';
    SetCookie( window.document.nameProp + '.ShowConfirm', false );
}
function ConfirmExit()
{
    var IsDirty = eval( GetCookie( window.document.nameProp + '.IsDirty' ) );
    var ShowConfirm = eval( GetCookie( window.document.nameProp + '.ShowConfirm' ) );
    if ( IsDirty && ShowConfirm )
    {
        event.returnValue = GetCookie( window.document.nameProp + '.ConfirmMsg' );
        __doPostBack( window.document.name, null );
    }
}
// This is needed just to turn Confirm back on if isDirty after SuppressConfirm
// was initiated by a button or tab control.
if ( eval( GetCookie( window.document.nameProp + '.IsDirty' ) ) )
    SetConfirmOn();


//================================================================
// FlashMessage
//
// Used by LoadAssignment and related pages
//----------------------------------------------------------------
function FlashMessage( text )
{
    try
    {
        var divFlashMessage = document.getElementById( "divFlashMessage" );

        if ( text == "" )
        {
            divFlashMessage.style.display = "none";
            divFlashMessage.innerHTML = "";
        }
        else
        {
            divFlashMessage.innerHTML = "<span>" + text + "</span>";
            divFlashMessage.style.display = "block";
        }
    }
    catch ( e ) { }
}

//================================================================
// Callback
//
// Default behavior when a callback is submitted
//----------------------------------------------------------------
function Callback()
{
    try { FlashMessage( _localizedStrings[14944] ); /* Please Wait... */ }
    catch ( e )
    {
        try { FlashMessage( _localizedStrings[14513] ); /* Processing... */ }
        catch ( e ) { }
    }
}

//================================================================
// CallbackError
//
// Default behavior when a callback fails
//----------------------------------------------------------------
function CallbackError()
{
    try
    {
        FlashMessage( _localizedStrings[14514] ); // Error. Please retry.
    }
    catch ( e ) { }
}

//================================================================
// CallbackComplete
//
// Default behavior when a callback is complete
//----------------------------------------------------------------
function CallbackComplete()
{
    try
    {
        FlashMessage( "" );
    }
    catch ( e ) { }
}

//================================================================
// Constants
//
// Used by Rail Load Assignment to get/set TreeViewNode.Value
//----------------------------------------------------------------
var TV_PARAMETER_SEPARATOR = '|';
var TV_KEY_VALUE_SEPARATOR = '=';

//================================================================
// GetNodeParameters 
//
// Used by Rail Load Assignment to get/set TreeViewNode.Value
//----------------------------------------------------------------
function GetNodeParameters( node )
{
    var parameters = new Array();
    var valueSplit = node.Value.split( TV_PARAMETER_SEPARATOR );

    for ( var i in valueSplit )
    {
        parameter = valueSplit[i];
        var keyValueArray = parameter.split( TV_KEY_VALUE_SEPARATOR );
        if ( keyValueArray.length == 2 )
        {
            parameters[keyValueArray[0]] = keyValueArray[1];
        }
    }
    return parameters;
}

//================================================================
// SetNodeParameters 
//
// Used by Rail Load Assignment to get/set TreeViewNode.Value
//----------------------------------------------------------------
function SetNodeParameters( node, parameters )
{
    var nodeValue = "";
    for ( var key in parameters )
    {
        value = parameters[key];
        nodeValue += key + TV_KEY_VALUE_SEPARATOR + value + TV_PARAMETER_SEPARATOR;
    }
    node.Value = nodeValue;
}

//================================================================
// SetNodeParameter 
//
// Used by Rail Load Assignment to get/set TreeViewNode.Value
//----------------------------------------------------------------
function SetNodeParameter( node, parameterName, parameterValue )
{
    var parameters = GetNodeParameters( node );

    parameters[parameterName] = parameterValue;

    SetNodeParameters( node, parameters );
}

//================================================================
// GetNodeParameter 
//
// Used by Rail Load Assignment to get/set TreeViewNode.Value
//----------------------------------------------------------------
function GetNodeParameter( node, parameterName )
{
    var parameters = GetNodeParameters( node );

    return parameters[parameterName];
}

//////////////////////////////////////////////////////////////
// TreeView Helper functions
//
// tv            = the TreeView control being updated.
// tvData        = the Tree View Data Array that holds all the data.
// itemId        = the Id of the item being updated.
// itemIdIndex   = the index of the itemId in tvData array.  Used in GetTreeViewIndex.
// newText       = the new Text for tvData to be changed to.
//////////////////////////////////////////////////////////////
function UpdateTreeViewNodeText( tv, tvData, itemId, itemIdIndex, newText )
{
    if ( tv && tvData )
    {
        var index = GetTreeViewIndex( tvData, itemId, itemIdIndex );
        var obj = tvData[index];
        obj[itemIdIndex][1][1] = newText;
        tvData[index] = obj;
        tv.Render();
    }
}

function GetTreeViewIndex( tvData, itemId, itemIdIndex )
{
    if ( tvData.length )
    {
        for ( var i = 0; i < tvData.length; i++ )
        {
            var obj = tvData[i];
            if ( obj[itemIdIndex][0][1] == itemId )
            {
                return i;
            }
        }
    }
}
//////////////////////////////////////////////////////////////
// Information Popup for UI.
//////////////////////////////////////////////////////////////
function PopupInfo( left, top, width, height, msg )
{
    var oPopup = window.createPopup();
    var oPopBody = oPopup.document.body;

    oPopBody.style.filter = "progid:DXImageTransform.Microsoft.Shadow(color='#666666', Direction=135, Strength=4)";
    oPopBody.style.border = "solid black 1px";
    oPopBody.style.padding = "7px";
    oPopBody.style.backgroundColor = "#ffffff";
    oPopBody.style.fontFamily = "Tahoma, Verdana, Arial, Helvetica, MS Sans Serif";
    oPopBody.style.fontWeight = "normal";
    oPopBody.style.fontSize = "11px";
    oPopBody.style.color = "#000000";
    oPopBody.innerHTML = msg;
    oPopup.show( left, top, width, height, document.body );
}
function PopupInfoHide()
{
    var oPopup = window.createPopup();
    oPopup.hide();
}

// ******************************************************************************************
// Functions used by the Device Search Control
// ******************************************************************************************
function OpenDeviceSearchPopup( clientId )
{
    var deviceCodeInput = eval( 'document.all.' + clientId + '_txtDeviceCode' );
    var deviceCode = deviceCodeInput.value;

    var path = document.location.pathname;
    var test = path.split( "/" );
    var realpath = "Searching/DeviceSearch.aspx";
    for ( i = 0; i < test.length; i++ )
    {
        realpath = "../" + realpath;
    }

    url = realpath + "?ClientId=" + clientId + "&DeviceCode=" + deviceCode;

    // Adjust for less than 1024x768
    var nScreenHeight = window.screen.height;

    if ( nScreenHeight < 770 )
    {
        nScreenHeight = 520;
    }
    else
    {
        nScreenHeight = 650;
    }

    OpenPopup( url, "DeviceSearch", 900, ( nScreenHeight + 32 ), "no", "no", "yes", "no" )
}

function Device_Changed( clientId )
{
    var txtbox = eval( 'document.all.' + clientId + '_txtDeviceCode' );
    if ( !txtbox.readOnly )
    {
        var txtbox1 = eval( 'document.all.' + clientId + '_txtDeviceId' );
        txtbox1.value = '';

        var txtbox2 = eval( 'document.all.' + clientId + '_txtVendorId' );
        txtbox2.value = '';

        var txtbox3 = eval( 'document.all.' + clientId + '_txtDeviceTypeDesc' );
        txtbox3.value = '';
    }
}

function SetDeviceValues( values, clientId )
{
    var answers = eval( values );

    if ( window.opener && !window.opener.closed )
    {
        var xBox1 = eval( 'window.opener.document.all.' + clientId + "_txtDeviceId" );
        xBox1.value = answers[0];

        var xBox2 = eval( 'window.opener.document.all.' + clientId + "_txtDeviceCode" );
        xBox2.value = answers[1];

        var xBox3 = eval( 'window.opener.document.all.' + clientId + "_txtDeviceTypeDesc" );
        xBox3.select();
        xBox3.value = answers[2];

        var xBox4 = eval( 'window.opener.document.all.' + clientId + "_txtVendorId" );
        xBox4.value = answers[3];

        window.close();
    }
}

//////////////////////////////////////////////////////////////
// DataGrid Hover & Select Effects ARE THESE STILL USED OR ARE THEY MATRIX GRID?
//////////////////////////////////////////////////////////////

// Applies a class on hover to a grid row
function HoverGridStyle( obj, style, selectClass )
{
    if ( obj.className == selectClass )
        return;
    else
        obj.className = style;
}

// Resets the row styles for a datagrid when another row is selected
function ResetCSS( childObj, selectClass, itemClass, altItemClass )
{
    if ( childObj )
    {
        var parentTable = GetParentByType( childObj, "TABLE" );
        if ( parentTable )
        {
            var elems = parentTable.getElementsByTagName( "TR" );
            for ( i = 0; i < elems.length; i++ )
            {
                if ( elems[i].className == selectClass )
                {
                    var nextClassName;

                    if ( elems[i + 1] != null ) // Peek at the next element's class
                        nextClassName = elems[i + 1].className;
                    else // Its null, so peek the previous element's class
                        nextClassName = elems[i - 1].className;

                    if ( nextClassName == itemClass )
                        elems[i].className = altItemClass;
                    else
                        elems[i].className = itemClass;
                }
            }
        }
    }
}

/////////////////////////////////////////////////////////////////////////////////////
// Set the Date and Time on the specified Controls to the Client local Date and Time
/////////////////////////////////////////////////////////////////////////////////////

function setDateTime( dateFormatId, dateId, timeId )
{
    var fd = new String();
    var d = new Date();
    var mm = d.getMonth() + 1;
    var dd = d.getDate();
    var yyyy = d.getFullYear();

    try
    {
        if ( document.getElementById( dateFormatId ) != null )
        {
            if ( document.getElementById( dateFormatId ).value == "dd/MM/yyyy" || document.getElementById( dateFormatId ).value == "d/MM/yyyy" )
            {
                fd = dd + "/" + mm + "/" + yyyy;
            }
            else if ( document.getElementById( dateFormatId ).value == "d/M/yyyy" )
            {
                fd = dd + "/" + mm + "/" + yyyy;
            }
            else if ( document.getElementById( dateFormatId ).value == "yyyy/MM/dd" || document.getElementById( dateFormatId ).value == "yyyy/M/d" )
            {
                fd = yyyy + "/" + mm + "/" + dd;
            }
            else if ( document.getElementById( dateFormatId ).value == "dd.MM.yyyy" )
            {
                fd = dd + "." + mm + "." + yyyy;
            }
            else
            {
                fd = mm + "/" + dd + "/" + yyyy;
            }

            if ( document.getElementById( dateId ) != null )
            {
                if ( document.getElementById( dateId ).value == "" )
                {
                    document.getElementById( dateId ).value = fd;
                }
            }
            if ( document.getElementById( timeId ) != null )
            {
                if ( document.getElementById( timeId ).value == "" )
                {
                    document.getElementById( timeId ).value = d.toLocaleTimeString();
                }
            }
        }
    }
    catch ( ex )
    {
    }
}
function setDateTimeWithoutSeconds( dateFormatId, dateId, timeId )
{

    var fd = new String();
    var d = new Date();
    var mm = d.getMonth() + 1;
    var dd = d.getDate();
    var yyyy = d.getFullYear();

    try
    {
        if ( document.getElementById( dateFormatId ) != null )
        {
            if ( document.getElementById( dateFormatId ).value == "dd/MM/yyyy" || document.getElementById( dateFormatId ).value == "d/MM/yyyy" )
            {
                fd = dd + "/" + mm + "/" + yyyy;
            }
            else if ( document.getElementById( dateFormatId ).value == "d/M/yyyy" )
            {
                fd = dd + "/" + mm + "/" + yyyy;
            }
            else if ( document.getElementById( dateFormatId ).value == "yyyy/MM/dd" || document.getElementById( dateFormatId ).value == "yyyy/M/d" )
            {
                fd = yyyy + "/" + mm + "/" + dd;
            }
            else if ( document.getElementById( dateFormatId ).value == "dd.MM.yyyy" )
            {
                fd = dd + "." + mm + "." + yyyy;
            }
            else
            {
                fd = mm + "/" + dd + "/" + yyyy;
            }

            if ( document.getElementById( dateId ) != null )
            {
                if ( document.getElementById( dateId ).value == "" )
                {
                    document.getElementById( dateId ).value = fd;
                }
            }
            if ( document.getElementById( timeId ) != null )
            {
                if ( document.getElementById( timeId ).value == "" )
                {
                    document.getElementById( timeId ).value = d.toLocaleTimeString().substring( 0, d.toLocaleTimeString().length - 6 ) + ' ' + d.toLocaleTimeString().substring( d.toLocaleTimeString().length - 2, d.toLocaleTimeString().length ); //d.toLocaleTimeString();
                }
            }
        }
    }
    catch ( ex )
    {
    }
}
function ReturnPackagingSearch( textboxID, packagingId, descriptionID, descriptionValue, persistName, typeIdentifier, packagingFieldID )
{
    if ( window.opener && !window.opener.closed )
    {
        var control = eval( 'window.opener.document.all.' + textboxID );
        if ( control )
        {
            control.value = typeIdentifier;
        }

        control = eval( 'window.opener.document.all.' + descriptionID );
        if ( control )
        {
            control.innerText = descriptionValue;
        }

        control = eval( 'window.opener.document.all.' + packagingFieldID );
        if ( control )
        {
            control.value = packagingId;
        }

        window.close();
    }
}
function ReturnSearch( textboxID, textboxValue, descriptionID, descriptionValue )
{
    if ( window.opener && !window.opener.closed )
    {
        var control = eval( 'window.opener.document.all.' + textboxID );
        if ( control )
        {
            control.value = textboxValue;
        }
        control = eval( 'window.opener.document.all.' + descriptionID );
        if ( control )
        {
            control.value = descriptionValue;
        }

        window.close();
    }
}

function ReturnImportExportCodeSearch( textboxID, textboxValue, descriptionID, descriptionValue, codeID, codeIdValue )
{

    if ( window.opener && !window.opener.closed )
    {
        var control = eval( 'window.opener.document.all.' + textboxID );
        if ( control )
        {
            control.value = textboxValue;
        }
        control = eval( 'window.opener.document.all.' + descriptionID );
        if ( control )
        {
            control.value = descriptionValue;
        }

        control = eval( 'window.opener.document.all.' + codeID );
        if ( control )
        {
            control.value = codeIdValue;
        }
        window.close();
    }
}


function SetUniqueRadioButton( nameregex, current )
{
    re = new RegExp( nameregex );
    for ( i = 0; i < document.forms[0].elements.length; i++ )
    {
        elm = document.forms[0].elements[i]
        if ( elm.type == 'radio' )
        {
            if ( re.test( elm.name ) )
            {
                elm.checked = false;
            }
        }
    }
    current.checked = true;
}

//////////////////////////////////////////////////////////////
// Show Powerunit Details 
//////////////////////////////////////////////////////////////
function PowerUnit_Details( powerUnitId )
{
    if ( powerUnitId == "" )
    {
        return;
    }

    var path = document.location.pathname;
    var test = path.split( "/" );
    var realpath = "AssetMaintenance/PowerUnitDetails.aspx";
    for ( i = 0; i < test.length; i++ )
    {
        realpath = "../" + realpath;
    }

    var url = realpath + "?powerUnitId=" + powerUnitId;

    // jrp: expand popup width- was a little too small
    OpenPopup( url, "PowerUnitDetails", 910, 540, "no", "no", "yes" );
}

//////////////////////////////////////////////////////////////
// Add Powerunit Details 
//////////////////////////////////////////////////////////////
function AddPowerUnit()
{

    var path = document.location.pathname;
    var test = path.split( "/" );
    var realpath = "AssetMaintenance/PowerUnitDetails.aspx";
    for ( i = 0; i < test.length; i++ )
    {
        realpath = "../" + realpath;
    }

    // jrp: expand popup width- was a little too small
    OpenPopup( realpath, "AddPowerUnit", 910, 540, "no", "no", "yes" );
}

//////////////////////////////////////////////////////////////
// Show conveyance Details 
//////////////////////////////////////////////////////////////
function Conveyance_Details( conveyanceId )
{
    if ( conveyanceId == "" )
    {
        return;
    }

    var path = document.location.pathname;
    var test = path.split( "/" );
    var realpath = "AssetMaintenance/ConveyanceDetails.aspx";
    for ( i = 0; i < test.length; i++ )
    {
        realpath = "../" + realpath;
    }

    var url = realpath + "?conveyanceId=" + conveyanceId;

    // jrp: expand popup width- was a little too small
    OpenPopup( url, "ConveyanceDetails", 910, 540, "no", "no", "yes" );
}

//////////////////////////////////////////////////////////////
// Add conveyance 
//////////////////////////////////////////////////////////////
function AddConveyance()
{

    var path = document.location.pathname;
    var test = path.split( "/" );
    var realpath = "AssetMaintenance/ConveyanceDetails.aspx";
    for ( i = 0; i < test.length; i++ )
    {
        realpath = "../" + realpath;
    }

    // jrp: expand popup width- was a little too small
    OpenPopup( realpath, "AddConveyance", 910, 540, "no", "no", "yes" );
}

//////////////////////////////////////////////////////////////
// Show Personnel Details 
//////////////////////////////////////////////////////////////
function Personnel_Details( personnelId )
{
    if ( personnelId == "" )
    {
        return;
    }

    var path = document.location.pathname;
    var test = path.split( "/" );
    var realpath = "AssetMaintenance/PersonnelMaintenanceDetails.aspx";
    for ( i = 0; i < test.length; i++ )
    {
        realpath = "../" + realpath;
    }

    var url = realpath + "?personnelId=" + personnelId;

    // jrp: expand popup width- was a little too small
    OpenPopup( url, "PersonnelDetails", 1050, 745, "no", "no", "yes" );
}

//////////////////////////////////////////////////////////////
// Add Personnel 
//////////////////////////////////////////////////////////////
function AddPersonnel()
{

    var path = document.location.pathname;
    var test = path.split( "/" );
    var realpath = "AssetMaintenance/PersonnelMaintenanceDetails.aspx";
    for ( i = 0; i < test.length; i++ )
    {
        realpath = "../" + realpath;
    }

    // jrp: expand popup width- was a little too small
    OpenPopup( realpath, "AddPersonnel", 1050, 745, "no", "no", "yes" );
}

////////////////////////////////////////////////////////
// Localized DateTime Functions
////////////////////////////////////////////////////////

function LocalizeDateTimeString( datetime, dateFormat, timeFormat )
{
    var datetimeString;

    var hours = datetime.getHours();
    var minutes = datetime.getMinutes();
    var date = datetime.getDate();
    var month = datetime.getMonth() + 1;
    var year = datetime.getFullYear();

    // date format
    if ( dateFormat != undefined )
    {
        if ( dateFormat == "dd/MM/yyyy" || dateFormat == "d/MM/yyyy" || dateFormat == "d/M/yyyy" )
        {

            datetimeString = date + "/" + month + "/" + year;
        }
        else if ( dateFormat == "yyyy/MM/dd" || dateFormat == "yyyy/M/d" )
        {

            datetimeString = year + "/" + month + "/" + date;
        }
        else if ( dateFormat == "dd.MM.yyyy" )
        {
            datetimeString = date + "." + month + "." + year;
        }
        else // mm/dd/yyyy
        {
            datetimeString = month + "/" + date + "/" + year;
        }
    }
    // time format
    if ( timeFormat != undefined )
    {
        if ( timeFormat == "HH:mm" )
        {
            datetimeString += " " + ( hours < 10 ? "0" + hours : hours ) + ":" + ( minutes < 10 ? "0" + minutes : minutes );
        }
        else if ( timeFormat == "H:mm" )
        {
            datetimeString += " " + hours + ":" + ( minutes < 10 ? "0" + minutes : minutes );
        }
    }
    return datetimeString;
}


////////////////////////////////////////////////////////
// Watermark Functions
////////////////////////////////////////////////////////

function WaterMarkOnFocus(txtbox, text)
{
    if (txtbox.value == text)
    {
        txtbox.style.color = "Black";
        txtbox.value = "";
    }
}

function WaterMarkOnBlur(txtbox, text)
{
    if (txtbox.value == "")
    {
        txtbox.style.color = "#8D9BAB";
        txtbox.value = text;
    }
}