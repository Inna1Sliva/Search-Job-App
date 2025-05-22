package com.it.shka.searchjobapp.screens


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.it.shka.data.model.Offer
import com.it.shka.data.model.Vacancy
import com.it.shka.searchjobapp.DataViewModel
import com.it.shka.searchjobapp.IconId
import com.it.shka.searchjobapp.R
import com.it.shka.searchjobapp.currentRoute
import com.it.shka.searchjobapp.dialog.DialogScreen
import com.it.shka.searchjobapp.dialog.DialogTwoScreen
import java.text.SimpleDateFormat
import java.util.Locale



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainSearch(viewModel:DataViewModel){
    var showDialog  =viewModel.stateDialog.collectAsState()
    var showTwoDialog by remember { mutableStateOf(false) }

    // val vacancy = viewModel.vacancyState.collectAsState()

    val navController  = rememberNavController()
    NavHost(
        navController= navController,
        startDestination= "start_search"
    ){
        composable("start_search"){
            SearchScreen(viewModel, navController)
        }
        composable("vacancies") {
            VacanciesScreen(viewModel, navController)
        }
        composable ("detaile"){
           DetailsScreen(viewModel)
            }
       dialog ("dialog") {
            DialogScreen(navController,viewModel)
       }

        dialog ("dialog_two"){
            DialogTwoScreen(navController, viewModel)
        }


        }

}



@Composable
fun SearchScreen(viewModel:DataViewModel, navController: NavController){
    val scrollState =rememberScrollState()
    val offer = viewModel.offerState.collectAsState()
    val _nVacancy = viewModel.nVacancyState.collectAsState()
    val vacancy = viewModel.vacancyState.collectAsState()
    //  val offer = viewModel.offerState.collectAsState(emptyList())


    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)
        .verticalScroll(scrollState)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .background(
                        color = colorResource(R.color.search_bag),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Row {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(width = 25.dp, height = 25.dp),
                            painter = painterResource(R.drawable.icon_search),
                            contentDescription = "",
                            tint = colorResource(R.color.Basic_Grey_4)
                        )
                    }
                    BasicTextField(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .wrapContentSize(),
                        value = "textWhere.value",
                        onValueChange = {//textWhere.value = it
                        },
                        decorationBox = {
                            Text(
                                modifier = Modifier
                                    .padding(end =16.dp)
                                    .align(Alignment.CenterVertically),
                               text =  "Должность, ключевые слова",
                                fontSize = 16.sp,
                                color = colorResource(R.color.search_text),
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Justify
                            )
                        }
                    )
                }

            }
            Spacer(
                modifier = Modifier
                    .width(16.dp)
            )

            IconButton(
                modifier = Modifier
                    .wrapContentSize()
                    .background(
                        color = colorResource(R.color.search_bag),
                        shape = RoundedCornerShape(8.dp)
                    ),
                onClick = {}
            ) {
                Icon(
                    modifier = Modifier
                        .size(width = 24.dp, height = 24.dp),
                    painter = painterResource(R.drawable.icon_filter), contentDescription = "",
                    tint = Color.White
                )
            }
        }//поиск

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        ) {
            items(listOf(offer)) { data ->
                data.value.forEach { offer->
                    ItemListOffer(offer, viewModel)
                }//items

            }
        }
        // текст вакансии
        Text(modifier = Modifier
            .padding(start = 16.dp, bottom = 16.dp, top = 20.dp)
            .fillMaxWidth()
            .background(Color.Black),
            text = "Вакансии для вас",
            color = Color.White,
            fontSize = 20.sp
        )
//вакансии
        LazyColumn(
            modifier = Modifier
                .height(1100.dp)
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)

        ) {

            items(listOf(_nVacancy)) { vacancy ->
                vacancy.value.forEach { vacancy ->
                    ItemListVacancy(vacancy, viewModel,navController)
                }

            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .background(color = colorResource(R.color.Special_Blue), shape = RoundedCornerShape(8.dp)),
            onClick = {navController.navigate("vacancies")},
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.Special_Blue),
                contentColor = colorResource(R.color.Special_Blue)
            )) {
            Text(getSizeVacancy(vacancy.value.size),
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }//end colum

}
@Composable
fun getSizeVacancy(vacancy: Int): String{
    return when(vacancy){
        0-> ""
        1->" 1 вакансия"
        else -> "Еще $vacancy вакансии"
    }
}
@Composable
fun ItemListVacancy(vacancy: Vacancy, viewModel: DataViewModel, navController: NavController) {


    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(bottom = 16.dp)
            .clickable {
                viewModel.setDetailVacancy(vacancy)
                navController.navigate("detaile")
            }
            .background(
                color = colorResource(R.color.vacancy_bag),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Column(
                modifier = Modifier
                    .width(250.dp)
            ) {
                //текст просмотр
                if (vacancy.lookingNumber?.isNotEmpty() == true) {
                    Text(
                        text = "Сейчас просматривает ${vacancy.lookingNumber} человек",
                        color = colorResource(R.color.button_color),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Left
                    )
                } else {
                    Text(
                        text = "",
                        color = colorResource(R.color.button_color),
                        fontSize = 14.sp
                    )
                }

                //наименование вакансии
                Text(
                    modifier = Modifier
                        .padding(top = 5.dp),
                    text = vacancy.title,
                    color = Color.White,
                    fontSize = 16.sp
                ) //1500-2900 Br
                //зп
                Text(
                    modifier = Modifier
                        .padding(top = 5.dp),
                    text = vacancy.full,
                    color = Color.White,
                    fontSize = 20.sp
                )


                //город
                Text(
                    modifier = Modifier
                        .padding(top = 5.dp),
                    text = vacancy.town,
                    color = Color.White,
                    fontSize = 14.sp
                )
                //компани
                Row(
                    modifier = Modifier
                        .padding(top = 5.dp)
                ) {
                    Text(
                        text = vacancy.company,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    Icon(
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .align(Alignment.CenterVertically)
                            .size(width = 16.dp, height = 16.dp),
                        painter = painterResource(R.drawable.icon_check),
                        contentDescription = "icon_check",
                        tint = colorResource(R.color.Basic_Grey_3)
                    )
                }
                //опыт работы //
                Row(
                    modifier = Modifier
                        .padding(top = 5.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .size(width = 16.dp, height = 16.dp),
                        painter = painterResource(R.drawable.icon_skill),
                        contentDescription = "icon_check",
                        tint = Color.White
                    )

                    Text(
                        modifier = Modifier
                            .padding(start = 5.dp),
                        text = vacancy.previewText,
                        color = Color.White,
                        fontSize = 14.sp
                    )

                }
                // дата публикации
                Text(
                    modifier = Modifier
                        .padding(top = 5.dp),
                    text = "${getMonthString(vacancy.publishedDate)}",
                    color = colorResource(R.color.Basic_Grey_3),
                    fontSize = 14.sp
                )

            }
            Spacer(
                modifier = Modifier
                    .width(50.dp)
            )
            //Добавить метод определения
            if (vacancy.favorite?.isNotEmpty() == true) {

                Icon(
                    modifier = Modifier
                        .size(width = 24.dp, height = 24.dp),
                    painter = painterResource(R.drawable.favorite),
                    tint = colorResource(R.color.Special_Blue),
                    contentDescription = "folover"
                )


            } else {

                Icon(
                    modifier = Modifier
                        .clickable {
                            viewModel.setIsFavorit(vacancy.id)
                        }
                        .size(width = 24.dp, height = 24.dp),
                    painter = painterResource(R.drawable.icon_favorits),
                    tint = colorResource(R.color.Basic_Grey_4),
                    contentDescription = "folover"
                )
            }


        }
        //button
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(
                    color = colorResource(R.color.button_color),
                    shape = RoundedCornerShape(50.dp)
                ),
            onClick = {
                viewModel.openDialog()
                navController.navigate("dialog")


            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.button_color),
                contentColor = colorResource(R.color.button_color)
            )
        ) {
            Text(
                text = "Откликнуться",
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }

}
@Composable
fun ItemListOffer(data: Offer, viewModel: DataViewModel){
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .size(width = 150.dp, height = 132.dp)
            .padding(end = 10.dp)
            .clickable{
                viewModel.openLinkOffer(context, data.link)
            }
            .background(
                color = colorResource(R.color.vacancy_bag),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            // Иконка
            val iconId = IconId.fromIconIdStrinf(data.id)
            val iconRes = iconId?.let { getIconResource(it) }
            if (iconRes != null) {
                Icon(
                    modifier = Modifier
                        .size(width = 32.dp, height = 32.dp),
                    painter = painterResource(id = iconRes),
                    contentDescription = data.id
                )
            } else {
                Spacer(
                    modifier = Modifier
                        .size(width = 32.dp, height = 32.dp)
                )
            }
            //Teкст
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                text = data.title,
                color = Color.White,
                fontSize = 14.sp,
                overflow = TextOverflow.Clip
            )
            //кнопка boolean
            if (data.button.isNotEmpty()) {
                Text(text = data.button, color = Color.Green, fontSize = 14.sp)
            } else {
                Text(text = "", color = Color.Green, fontSize = 14.sp)

            }
        }
    }
}
@Composable
fun getMonthString(dataString: String): String{
    val inputFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    val outputFormat = SimpleDateFormat("d MMMM", Locale("ru"))
    val date = inputFormat.parse(dataString) ?: return "Некорректная дата"
    val formattedDate = outputFormat.format(date)
    return "Опубликовано $formattedDate"
}
@Composable
fun getIconResource(iconId: IconId): Int{
return when(iconId){
    IconId.NEAR_VACANCIES -> R.drawable.icon_location
    IconId.LEVEL_UP_RESURS -> R.drawable.icon_level_up_resume
    IconId.TEMPORARY_JOB -> R.drawable.icon_temporary_job
}
}
