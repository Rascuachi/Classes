package com.ita.myapp.classes.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.ita.myapp.classes.R
import com.ita.myapp.classes.data.model.MenuModel
import com.ita.myapp.classes.data.model.PostModel
import com.ita.myapp.classes.ui.components.PostCard
import com.ita.myapp.classes.ui.components.PostCardCompact
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

//import java.lang.reflect.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentsScreen(navController: NavController) {
    var menuOptions = arrayOf(
        MenuModel(1,"Content 1","Content1",Icons.Filled.Home),
        MenuModel(2,"Content 2","Content2",Icons.Filled.Person),
        MenuModel(3,"Buttons","Buttons",Icons.Filled.Build),
        MenuModel(4,"Floating Buttons","FloatingButtons",Icons.Filled.AddCircle),
        MenuModel(5,"Chips","Chips",Icons.Filled.Info),
        MenuModel(6,"Progress","Progress",Icons.Filled.Check),
        MenuModel(7,"Sliders","Sliders",Icons.Filled.Favorite),
        MenuModel(8,"Switches","Switches",Icons.Filled.Home),
        MenuModel(9,"Badges","Badges",Icons.Filled.ShoppingCart),
        MenuModel(10,"TimePickers","TimePickers",Icons.Filled.Notifications),
        MenuModel(11,"DatePickers","DatePickers",Icons.Filled.DateRange),
        MenuModel(12,"AlertDialogs","AlertDialogs",Icons.Filled.Warning),
        MenuModel(13,"SnackBars","SnackBars",Icons.Filled.Settings),
        MenuModel(14,"Bars","Bars",Icons.Filled.Person),
        MenuModel(15,"Adaptive","Adaptive",Icons.Filled.Warning)
    )
    var component by rememberSaveable{ mutableStateOf("") } //Can assign a value

    var drawerState = rememberDrawerState(initialValue= DrawerValue.Closed)
    val scope = rememberCoroutineScope() //Update drawer state, is it closed?
    ModalNavigationDrawer( //Screen that displays above our content
        drawerState=drawerState, //Current state of drawer
        // drawer content
        drawerContent = { //Content of menu
            ModalDrawerSheet {
                Text("Menu",
                    modifier = Modifier
                        .padding(16.dp))
                HorizontalDivider() // Line
                LazyColumn{
                    items(menuOptions){
                            item ->
                        NavigationDrawerItem(
                            icon = {Icon(item.icon, contentDescription = null)},
                            label = { Text(item.title) },
                            selected = false,
                            onClick = {
                                component=item.option
                                scope.launch {
                                    drawerState.apply {
                                        close() // Close drawer or side menu
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }) {
        Column{
            when(component){
                "Content1" -> {
                    Content1()
                }
                "Content2" ->{
                    Content2()
                }
                "Buttons" ->{
                    Buttons()
                }
                "Floating" ->{
                    FloatingButtons()
                }
                "Chips" ->{
                    Chips()
                }
                "Progress"->{
                    Progress()
                }
                "Sliders"->{
                    Sliders()
                }
                "Switches"->{
                    Switches()
                }
                "Badges"->{
                    Badges()
                }
                "TimePickers" ->{
                    ShowTimePicker()
                }
                "DatePickers"->{
                    DatePickerDocked()
                }
                "AlertDialogs"->{ //Notify the user a task done
                    AlertDialogs()
                }
                "SnackBars"->{ //A dialog prompt to confirm
                    SnackBars()
                }
                "Bars"->{
                    Bars()
                }
                "Adaptive"->{
                    Adaptive()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Content1(){
    Text(text="Content 1")
}

@Preview(showBackground = true)
@Composable
fun Content2(){
    Text(text="Content 2")
}

//@Preview(showBackground = true)
@Composable
fun Buttons(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ){
        Button(onClick = {}){
            Text("Filled")
        }
        FilledTonalButton(onClick = {}){ //Button with lower color
            Text("Tonal")
        }
        OutlinedButton(onClick = {}){ // Only border
            Text("Outlined")
        }
        ElevatedButton(onClick = {}) { //Buton with shadow
            Text("Elevated")
        }
        TextButton(onClick = { }) { //Hyperlink
            Text("Text")
        }
    }
}
//@Preview(showBackground = true)
@Composable
fun FloatingButtons() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        FloatingActionButton(onClick = { }) {
            Icon(Icons.Filled.Add,"")
        }
        SmallFloatingActionButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.Add,"")
        }
        LargeFloatingActionButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.Add,"")
        }
        ExtendedFloatingActionButton(
            onClick = { /*TODO*/ },
            icon = {Icon(Icons.Filled.Add,"")},
            text = {Text("Extended")}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Chips() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        AssistChip(
            onClick = { /*TODO*/ },
            label = { Text("Assist Chip") },
            leadingIcon={Icon(Icons.Filled.Add,"",
                modifier= Modifier.size(AssistChipDefaults.IconSize) // Stays resizeable according to chip
            )
            }
        )

        var selected by remember { mutableStateOf(false) } // It can change the interface, initial value is false
        FilterChip(
            selected = selected,
            onClick = { selected = !selected},
            label = { Text("Toggle") },
            leadingIcon= {
                if(selected){
                    Icon(Icons.Filled.Add,"")
                }else{
                    null
                }
            }
        )
        InputChipExample(text = "Dismiss",{})

    }
}


@Composable
fun InputChipExample(
    text : String,
    onDismiss: () -> Unit
){
    var enabled by remember {mutableStateOf(true)} // true as default value
    if(!enabled) return // Returns nothing

    InputChip(
        label = { Text(text) },
        selected = enabled,
        onClick = {
            onDismiss()
            enabled = ! enabled
        },
        avatar = { //Icon
            Icon(
                Icons.Filled.Person,
                contentDescription = "",
                modifier=Modifier.size(InputChipDefaults.AvatarSize),
            )
        },
        trailingIcon = { //End Icon
            Icon(
                Icons.Filled.Close,
                contentDescription = "",
                modifier=Modifier.size(InputChipDefaults.AvatarSize),
            )
        }
    )
}




@Preview(showBackground = true)
@Composable
fun Progress() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
        ) // Progress line
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun Sliders() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        //Float 50
        var sliderPosition by remember{ mutableStateOf(50f) }

        Column{
            Slider(
                value = sliderPosition,
                onValueChange = {sliderPosition = it}, //Updates sliderPosition value
                steps= 10, //Increments when moving the slider
                valueRange = 0f..100f //Min and Max values

            )
            Text(
                text=sliderPosition.toString(),
                modifier=Modifier.fillMaxWidth(),
                textAlign= TextAlign.Center,
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun Switches() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        var checked by remember{mutableStateOf(true)}
        Switch(
            checked=checked,
            onCheckedChange={
                checked = it
            }
        )

        var checked2 by remember{mutableStateOf(true)}
        Switch(
            checked=checked2,
            onCheckedChange={
                checked2 = it
            },
            thumbContent = if (checked2){
                {Icon(
                    Icons.Filled.Check,
                    contentDescription = "",
                    modifier=Modifier.size(InputChipDefaults.AvatarSize),
                )}
            }
            else{null}
        )

        var checked3 by remember{mutableStateOf(true)}
        Checkbox(checked = checked3, onCheckedChange = {checked3 = it})
    }
}


@Preview(showBackground = true)
@Composable
fun Badges() { // Alerts in some icons or sections
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        var itemCount by remember{mutableStateOf(0)}

        BadgedBox(
            badge = {
                if (itemCount > 0) {
                    Badge(
                        containerColor = Color.Red, // Background
                        contentColor = Color.White// Text
                    ){
                        Text("$itemCount")
                    }
                }
            }
        ) {
            Icon(Icons.Filled.ShoppingCart,"")
        }
        Button(
            onClick = {itemCount++}
        ){
            Text("Add Item")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DatePickers1() { // Alerts in some icons or sections
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        DatePickerDocked()
        //DatePickerModal(onDateSelected = {}, onDismiss = {})
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDocked() {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedDate,
            onValueChange = { },
            label = { Text("DOB") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = !showDatePicker }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select date"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        )

        if (showDatePicker) {
            Popup(
                onDismissRequest = { showDatePicker = false },
                alignment = Alignment.TopStart
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 64.dp)
                        .shadow(elevation = 4.dp)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                ) {
                    DatePicker(
                        state = datePickerState,
                        showModeToggle = false
                    )
                }
            }
        }
    }
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Composable
fun ShowTimePicker(){
    TimePickers1(onDismiss = {}, onConfirm = {})
}

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
fun TimePickers1(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) { // Alerts in some icons or sections
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        val currentTime = Calendar.getInstance()

        val timePickerState = rememberTimePickerState(
            initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
            initialMinute = currentTime.get(Calendar.MINUTE),
            is24Hour = true,
        )

        Column {
            TimePicker(
                state = timePickerState,
            )
            Button(onClick = onDismiss) {
                Text("Dismiss picker")
            }
            Button(onClick = onConfirm) {
                Text("Confirm selection")
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun SnackBars() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        val snackState = remember { SnackbarHostState() }
        val snackScope = rememberCoroutineScope()

        SnackbarHost(hostState= snackState,Modifier)

        fun launchSnackBar(){
            snackScope.launch { snackState.showSnackbar("The message was sent") }
        }
        Button(::launchSnackBar){
            Text(text = "Show Snackbar")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun AlertDialogs() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        var showAlertDialog by remember { mutableStateOf(false) }
        var selectedOption by remember { mutableStateOf("") }

        if(showAlertDialog){
            AlertDialog(
                icon = { Icon(Icons.Filled.Warning, contentDescription = "")},
                title = {Text("Confirm deletion")},
                text = {Text("Are you sure you want to delete the file?")},
                onDismissRequest = {},
                confirmButton = {
                    TextButton(onClick = {
                        selectedOption = "Confirm"
                        showAlertDialog=false
                    }) {
                        Text(text = "Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        selectedOption = "Dismiss"
                        showAlertDialog=false
                    }) {
                        Text(text = "Dismiss")
                    }
                }
            )
        }
        Text(text=selectedOption) //First is empty
        Button(onClick = {showAlertDialog=true}) {
            Text("Show alert dialog")
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun Bars() {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.DarkGray)
    ){
        Row( //Custom Header
            modifier= Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .background(Color.Black)
                .padding(10.dp, 50.dp, 10.dp, 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Icon(Icons.Filled.Menu, "", tint = Color.White)
            Text(
                "App Title",
                color=Color.White,
                fontWeight = FontWeight.Bold,
                fontSize=20.sp
            )
            Icon(Icons.Filled.Settings, "", tint = Color.White)
        }

        var post = arrayOf(
            PostModel(1,"Title1","Text1",painterResource(R.drawable.radio)),
            PostModel(2,"Title2","Text2",painterResource(R.drawable.radio)),
            PostModel(3,"Title3","Text3",painterResource(R.drawable.radio)),
            PostModel(4,"Title4","Text4",painterResource(R.drawable.radio)),
            PostModel(5,"Title5","Text5",painterResource(R.drawable.radio)),
            PostModel(6,"Title6","Text6",painterResource(R.drawable.radio)),
            PostModel(7,"Title7","Text7",painterResource(R.drawable.radio)),
            PostModel(8,"Title8","Text8",painterResource(R.drawable.radio)),
            PostModel(9,"Title9","Text9",painterResource(R.drawable.radio)),
            PostModel(10,"Title10","Text10",painterResource(R.drawable.radio)),
        )
        PostGrid(arrayPosts = post)
        Row( //Bottom Nav Bar
            modifier= Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.Black)
                .height(60.dp)
                .padding(2.dp, 5.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){ //Bottom Bar
            /**
             * Icons on the bottom nav bar
             */
            Column{
                IconButton(onClick={}, modifier = Modifier.size(30.dp)){
                    Icon(Icons.Outlined.Search,
                        contentDescription="",
                        tint=Color.White,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text("Search", color=Color.White)
            }
            Column{
                IconButton(onClick={}, modifier = Modifier.size(30.dp)){
                    Icon(Icons.Outlined.Menu,
                        contentDescription="",
                        tint=Color.White,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text("Men", color=Color.White)
            }
            Column{
                IconButton(onClick={}, modifier = Modifier.size(30.dp)){
                    Icon(Icons.Outlined.Home,
                        contentDescription="",
                        tint=Color.White,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text("Home", color=Color.White)
            }
            Column{
                IconButton(onClick={}, modifier = Modifier.size(30.dp)){
                    Icon(Icons.Outlined.Settings,
                        contentDescription="",
                        tint=Color.White,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text("Settings", color=Color.White)
            }
        }
    }
}

@Composable
fun Posts(arrayPosts : Array<PostModel>,adaptive:String){
    LazyColumn(

        modifier = Modifier
            .padding(10.dp, 90.dp, 10.dp, 50.dp) // Considering space of bars
            .fillMaxSize()
    ){
        items(arrayPosts){ // For each
                post ->
            when(adaptive){
                "PhoneP"->{
                    PostCardCompact(id = post.id, title = post.title, text = post.text, image = post.image)
                }
                "PhoneL"->{
                    PostCard(id = post.id, title = post.title, text = post.text, image = post.image)
                }
            }

        }
    }
}

@Composable
fun PostGrid(arrayPosts : Array<PostModel>){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),

        modifier = Modifier
            .padding(10.dp, 90.dp, 10.dp, 50.dp) // Considering space of bars
            .fillMaxSize()
    ){
        items(arrayPosts){ // For each
                post ->

            PostCard(id = post.id, title = post.title, text = post.text, image = post.image)
        }
    }
}

@Preview(showBackground = true,device ="spec:id=reference_tablet,shape=Normal,width=1280,height=800,unit=dp,dpi=240")
@Composable
fun Adaptive(){
    // Stores the dimensions of the actual screen
    var WindowsSize = currentWindowAdaptiveInfo().windowSizeClass

    //Sets variables with the height and width of the screen
    var height = currentWindowAdaptiveInfo().windowSizeClass.windowHeightSizeClass
    var width = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass

    var post = arrayOf(
        PostModel(1,"Title1","Text1",painterResource(R.drawable.radio)),
        PostModel(2,"Title2","Text2",painterResource(R.drawable.radio)),
        PostModel(3,"Title3","Text3",painterResource(R.drawable.radio)),
        PostModel(4,"Title4","Text4",painterResource(R.drawable.radio)),
        PostModel(5,"Title5","Text5",painterResource(R.drawable.radio)),
        PostModel(6,"Title6","Text6",painterResource(R.drawable.radio)),
        PostModel(7,"Title7","Text7",painterResource(R.drawable.radio)),
        PostModel(8,"Title8","Text8",painterResource(R.drawable.radio)),
        PostModel(9,"Title9","Text9",painterResource(R.drawable.radio)),
        PostModel(10,"Title10","Text10",painterResource(R.drawable.radio)),
    )
    if(width == WindowWidthSizeClass.COMPACT){
        Posts(post, "PhoneP") //PhoneP = Phone PORTRAIT
    }else if(height == WindowHeightSizeClass.COMPACT){
        Posts(post, "PhoneL") //PhoneP = Phone LANDSCAPE
    }else{
        Posts(post, "PhoneL")
    }
}