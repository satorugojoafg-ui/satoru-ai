package com.example.ui.screens.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.GlassCard
import com.example.ui.theme.CosmicBackground
import com.example.ui.theme.CosmicBackgroundSecondary
import com.example.ui.theme.CosmicCardBorder
import com.example.ui.theme.CosmicCardSurface
import com.example.ui.theme.CosmicCardSurfaceElevated
import com.example.ui.theme.NeonBlue
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonPurple
import com.example.ui.theme.SuccessGreen
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.WarmGold
import com.example.viewmodel.GojoViewModel

@Composable
fun ProfileScreen(viewModel: GojoViewModel) {
    val userProfile by viewModel.userProfile.collectAsState()
    val showApiKeyDialog by viewModel.showApiKeyDialog.collectAsState()
    val apiKeyInput by viewModel.apiKeyInput.collectAsState()
    val context = LocalContext.current

    var showEditProfileDialog by remember { mutableStateOf(false) }
    var editName by remember { mutableStateOf(userProfile?.name ?: "رضا") }
    var editEmail by remember { mutableStateOf(userProfile?.email ?: "reza@example.com") }

    var showNotificationsDialog by remember { mutableStateOf(false) }
    var showHistoryDialog by remember { mutableStateOf(false) }
    var showAboutDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CosmicBackground)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.statusBarsPadding())
                // Top Header
                Text(
                    text = "پروفایل کاربری",
                    color = TextPrimary,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            // User Identity Card
            item {
                GlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = CosmicCardSurface.copy(alpha = 0.9f)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(74.dp)
                                .clip(CircleShape)
                                .border(2.dp, NeonPurple, CircleShape)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.reza_avatar),
                                contentDescription = "آواتار کاربر",
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = userProfile?.name ?: "رضا",
                                    color = TextPrimary,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "تأیید شده",
                                    tint = NeonCyan,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = userProfile?.email ?: "reza.rezaei@example.com",
                                color = TextMuted,
                                fontSize = 12.sp
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(NeonPurple.copy(alpha = 0.25f))
                                        .padding(horizontal = 8.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "کاربر پرو",
                                        color = NeonPurple,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "${userProfile?.creditsRemaining ?: 850} اعتبار",
                                    color = WarmGold,
                                    fontSize = 11.sp
                                )
                            }
                        }

                        IconButton(
                            onClick = {
                                editName = userProfile?.name ?: "رضا"
                                editEmail = userProfile?.email ?: "reza@example.com"
                                showEditProfileDialog = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "ویرایش اطلاعات",
                                tint = NeonCyan,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }

            // Subscription Status Card
            item {
                GlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = CosmicCardSurface.copy(alpha = 0.95f),
                    borderColor = NeonCyan.copy(alpha = 0.4f)
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = userProfile?.planName ?: "اشتراک پیشرفته Gojo Pro",
                                    color = TextPrimary,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "تمدید خودکار: تا پایان سال ۱۴۰۵",
                                    color = TextMuted,
                                    fontSize = 11.sp
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(SuccessGreen.copy(alpha = 0.2f))
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "فعال",
                                    color = SuccessGreen,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            // Options List
            item {
                Text(
                    text = "تنظیمات و امکانات",
                    color = TextSecondary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            item {
                ProfileOptionRow(
                    title = "کلید هوش مصنوعی Gemini API",
                    subtitle = if (userProfile?.customApiKey.isNullOrBlank()) "پیش‌فرض سیستم (فعال)" else "کلید اختصاصی ثبت شده",
                    icon = Icons.Default.Key,
                    iconTint = NeonCyan,
                    onClick = { viewModel.openApiKeyDialog() }
                )
            }

            item {
                ProfileOptionRow(
                    title = "تاریخچه گفتگوها و پرامپت‌ها",
                    subtitle = "مرور و جستجو در مکالمات قبلی",
                    icon = Icons.Default.History,
                    iconTint = NeonPurple,
                    onClick = { showHistoryDialog = true }
                )
            }

            item {
                ProfileOptionRow(
                    title = "پیام‌ها و اعلانات",
                    subtitle = "به‌روزرسانی‌های مدل Gemini 3.5 Flash",
                    icon = Icons.Default.Notifications,
                    iconTint = WarmGold,
                    onClick = { showNotificationsDialog = true }
                )
            }

            item {
                ProfileOptionRow(
                    title = "درباره Gojo AI",
                    subtitle = "نسخه ۱.۰.۰ • مجهز به Gemini",
                    icon = Icons.Default.Info,
                    iconTint = NeonBlue,
                    onClick = { showAboutDialog = true }
                )
            }

            item {
                ProfileOptionRow(
                    title = "خروج از حساب کاربری",
                    subtitle = "پایان نشست فعلی",
                    icon = Icons.AutoMirrored.Filled.Logout,
                    iconTint = Color(0xFFFF5252),
                    onClick = {
                        viewModel.logout()
                        Toast.makeText(context, "با موفقیت خارج شدید", Toast.LENGTH_SHORT).show()
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(90.dp))
            }
        }

        // Edit Profile Dialog
        if (showEditProfileDialog) {
            AlertDialog(
                onDismissRequest = { showEditProfileDialog = false },
                containerColor = CosmicCardSurface,
                title = { Text("ویرایش پروفایل", color = TextPrimary, fontWeight = FontWeight.Bold) },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        OutlinedTextField(
                            value = editName,
                            onValueChange = { editName = it },
                            label = { Text("نام") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = NeonCyan,
                                unfocusedBorderColor = CosmicCardBorder,
                                focusedTextColor = TextPrimary,
                                unfocusedTextColor = TextPrimary
                            )
                        )
                        OutlinedTextField(
                            value = editEmail,
                            onValueChange = { editEmail = it },
                            label = { Text("ایمیل") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = NeonCyan,
                                unfocusedBorderColor = CosmicCardBorder,
                                focusedTextColor = TextPrimary,
                                unfocusedTextColor = TextPrimary
                            )
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.updateProfile(editName, editEmail)
                            showEditProfileDialog = false
                            Toast.makeText(context, "اطلاعات ذخیره شد", Toast.LENGTH_SHORT).show()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = NeonPurple)
                    ) {
                        Text("ذخیره", color = Color.White)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showEditProfileDialog = false }) {
                        Text("انصراف", color = TextMuted)
                    }
                }
            )
        }

        // Gemini API Key Dialog
        if (showApiKeyDialog) {
            var tempKey by remember { mutableStateOf(userProfile?.customApiKey.orEmpty()) }
            AlertDialog(
                onDismissRequest = { viewModel.closeApiKeyDialog() },
                containerColor = CosmicCardSurface,
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Key, contentDescription = null, tint = NeonCyan, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("تنظیم کلید Gemini API", color = TextPrimary, fontWeight = FontWeight.Bold)
                    }
                },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = "اپلیکیشن Gojo AI به‌طور مستقیم به موتور ابری Gemini 3.5 Flash وصل است. در صورت تمایل می‌توانید کلید اختصاصی گوگل خود را اینجا وارد کنید:",
                            color = TextSecondary,
                            fontSize = 12.sp,
                            lineHeight = 18.sp
                        )
                        OutlinedTextField(
                            value = tempKey,
                            onValueChange = { tempKey = it },
                            placeholder = { Text("AIzaSy...", color = TextMuted, fontSize = 11.sp) },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = NeonCyan,
                                unfocusedBorderColor = CosmicCardBorder,
                                focusedTextColor = TextPrimary,
                                unfocusedTextColor = TextPrimary
                            )
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = { viewModel.saveApiKey(tempKey) },
                        colors = ButtonDefaults.buttonColors(containerColor = NeonPurple)
                    ) {
                        Text("ذخیره کلید", color = Color.White)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { viewModel.closeApiKeyDialog() }) {
                        Text("بستن", color = TextMuted)
                    }
                }
            )
        }

        // History Dialog
        if (showHistoryDialog) {
            AlertDialog(
                onDismissRequest = { showHistoryDialog = false },
                containerColor = CosmicCardSurface,
                title = { Text("تاریخچه گفتگوها", color = TextPrimary, fontWeight = FontWeight.Bold) },
                text = {
                    Text(
                        text = "تمام گفتگوها و خروجی‌های شما در پایگاه داده محلی (Room) نگهداری می‌شوند و همیشه در تب «چت» در دسترس هستند.",
                        color = TextSecondary,
                        fontSize = 13.sp
                    )
                },
                confirmButton = {
                    TextButton(onClick = { showHistoryDialog = false }) {
                        Text("متوجه شدم", color = NeonCyan)
                    }
                }
            )
        }

        // Notifications Dialog
        if (showNotificationsDialog) {
            AlertDialog(
                onDismissRequest = { showNotificationsDialog = false },
                containerColor = CosmicCardSurface,
                title = { Text("پیام‌ها و اعلانات سیستم", color = TextPrimary, fontWeight = FontWeight.Bold) },
                text = {
                    Text(
                        text = "• اتصال به موتور قدرتمند Gemini 3.5 Flash برقرار است.\n• قابلیت تحلیل متون، داده‌ها و ایده‌پردازی با حداکثر سرعت فعال است.",
                        color = TextSecondary,
                        fontSize = 13.sp,
                        lineHeight = 22.sp
                    )
                },
                confirmButton = {
                    TextButton(onClick = { showNotificationsDialog = false }) {
                        Text("بستن", color = NeonCyan)
                    }
                }
            )
        }

        // About Dialog
        if (showAboutDialog) {
            AlertDialog(
                onDismissRequest = { showAboutDialog = false },
                containerColor = CosmicCardSurface,
                title = { Text("درباره Gojo AI", color = TextPrimary, fontWeight = FontWeight.Bold) },
                text = {
                    Text(
                        text = "Gojo AI یک پلتفرم هوش مصنوعی چندمنظوره فارسی است که برای مدیریت امور روزمره، تولید محتوا، تحلیل داده‌ها و برنامه‌ریزی پروژه‌ها طراحی شده است.",
                        color = TextSecondary,
                        fontSize = 13.sp,
                        lineHeight = 20.sp
                    )
                },
                confirmButton = {
                    TextButton(onClick = { showAboutDialog = false }) {
                        Text("باشه", color = NeonCyan)
                    }
                }
            )
        }
    }
}

@Composable
fun ProfileOptionRow(
    title: String,
    subtitle: String,
    icon: ImageVector,
    iconTint: Color,
    onClick: () -> Unit
) {
    GlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        backgroundColor = CosmicCardSurface.copy(alpha = 0.85f)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(iconTint.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column {
                    Text(
                        text = title,
                        color = TextPrimary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = subtitle,
                        color = TextMuted,
                        fontSize = 11.sp
                    )
                }
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                tint = TextMuted,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
