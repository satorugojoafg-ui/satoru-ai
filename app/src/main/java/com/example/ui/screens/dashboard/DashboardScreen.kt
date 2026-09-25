package com.example.ui.screens.dashboard

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
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.GlassCard
import com.example.ui.components.NavigationTab
import com.example.ui.theme.CosmicBackground
import com.example.ui.theme.CosmicCardBorder
import com.example.ui.theme.CosmicCardSurface
import com.example.ui.theme.NeonBlue
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonPink
import com.example.ui.theme.NeonPurple
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.WarmGold
import com.example.viewmodel.GojoViewModel

@Composable
fun DashboardScreen(viewModel: GojoViewModel) {
    val userProfile by viewModel.userProfile.collectAsState()
    val allProjects by viewModel.allProjects.collectAsState()
    var quickPromptText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CosmicBackground)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            item {
                Spacer(modifier = Modifier.statusBarsPadding())
                // Top Welcome Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "سلام ${userProfile?.name ?: "رضا"} عزیز!",
                                color = TextPrimary,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = "👋", fontSize = 20.sp)
                        }
                        Text(
                            text = "امروز چطور می‌تونم به پیشرفت کارهات کمک کنم؟",
                            color = TextSecondary,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }

                    // Avatar
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .border(2.dp, NeonPurple, CircleShape)
                            .clickable { viewModel.setTab(NavigationTab.PROFILE) }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.reza_avatar),
                            contentDescription = "پروفایل کاربر",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }

            // Gojo Pro Banner Card
            item {
                GlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = CosmicCardSurface.copy(alpha = 0.95f),
                    borderColor = NeonPurple.copy(alpha = 0.4f)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(NeonPurple.copy(alpha = 0.2f))
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = "Gojo AI Pro",
                                        color = NeonCyan,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "موتور Gemini 3.5 Flash",
                                    color = TextMuted,
                                    fontSize = 11.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "دسترسی نامحدود به ابزارهای تحلیل و خلاقیت هوش مصنوعی",
                                color = TextPrimary,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = NeonPurple,
                            modifier = Modifier
                                .size(36.dp)
                                .padding(end = 4.dp)
                        )
                    }
                }
            }

            // 3 Stat Cards (فایل‌های منتظر, درخواست‌های اخیر, اعتبار هوش مصنوعی)
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    StatMiniCard(
                        title = "فایل‌های در انتظار",
                        value = "${userProfile?.pendingFiles ?: 3}",
                        unit = "فایل",
                        color = NeonCyan,
                        modifier = Modifier.weight(1f)
                    )
                    StatMiniCard(
                        title = "درخواست‌های اخیر",
                        value = "۲۸",
                        unit = "درخواست",
                        color = NeonPurple,
                        modifier = Modifier.weight(1f)
                    )
                    StatMiniCard(
                        title = "اعتبار باقی‌مانده",
                        value = "${userProfile?.creditsRemaining ?: 850}",
                        unit = "توکن",
                        color = WarmGold,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // AI Suggestions / Tools Header
            item {
                Text(
                    text = "پیشنهادات هوش مصنوعی",
                    color = TextPrimary,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }

            // 4 AI Suggestion Cards Grid
            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        AiActionCard(
                            title = "تولید محتوا",
                            subtitle = "پست، مقاله و سناریو",
                            icon = Icons.Default.Description,
                            accentColor = NeonPurple,
                            modifier = Modifier.weight(1f),
                            onClick = { viewModel.sendQuickPrompt("لطفاً یک متن جذاب برای پست لینکدین با موضوع نوآوری در کسب‌وکار بنویس") }
                        )
                        AiActionCard(
                            title = "تحلیل هوشمند داده",
                            subtitle = "نمودارها و ارقام فروش",
                            icon = Icons.Default.Analytics,
                            accentColor = NeonCyan,
                            modifier = Modifier.weight(1f),
                            onClick = { viewModel.sendQuickPrompt("چطور داده‌های فروش ماهانه را برای یافتن نقاط رشد تحلیل کنم؟") }
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        AiActionCard(
                            title = "ساخت تصویر و ایده",
                            subtitle = "پرامپت‌های خلاقانه بصری",
                            icon = Icons.Default.Image,
                            accentColor = NeonPink,
                            modifier = Modifier.weight(1f),
                            onClick = { viewModel.sendQuickPrompt("ایده‌های خلاقانه طراحی بنر تبلیغاتی برای محصول نرم‌افزاری پیشنهاد بده") }
                        )
                        AiActionCard(
                            title = "ایده‌پردازی استارتاپ",
                            subtitle = "مدل کسب‌وکار و MVP",
                            icon = Icons.Default.Lightbulb,
                            accentColor = WarmGold,
                            modifier = Modifier.weight(1f),
                            onClick = { viewModel.sendQuickPrompt("۳ ایده مقیاس‌پذیر برای استارتاپ مبتنی بر هوش مصنوعی در ایران بگو") }
                        )
                    }
                }
            }

            // Quick Prompt Bar
            item {
                GlassCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    shape = RoundedCornerShape(16.dp),
                    backgroundColor = CosmicCardSurface.copy(alpha = 0.9f)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = quickPromptText,
                            onValueChange = { quickPromptText = it },
                            placeholder = { Text("هر سؤالی داری مستقیم بپرس...", color = TextMuted, fontSize = 13.sp) },
                            modifier = Modifier
                                .testTag("input_quick_prompt")
                                .weight(1f),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                focusedTextColor = TextPrimary,
                                unfocusedTextColor = TextPrimary
                            )
                        )

                        Box(
                            modifier = Modifier
                                .testTag("button_quick_send")
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(
                                    Brush.linearGradient(
                                        colors = listOf(NeonBlue, NeonPurple)
                                    )
                                )
                                .clickable {
                                    if (quickPromptText.isNotBlank()) {
                                        val prompt = quickPromptText
                                        quickPromptText = ""
                                        viewModel.sendQuickPrompt(prompt)
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Send,
                                contentDescription = "ارسال سریع",
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }

            // Recent Projects & Campaigns Section
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "پروژه‌های اخیر شما",
                        color = TextPrimary,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "مشاهده همه",
                        color = NeonCyan,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.clickable { viewModel.setTab(NavigationTab.PROJECTS) }
                    )
                }
            }

            // Show top 3 recent projects
            items(allProjects.take(3).size) { index ->
                val project = allProjects[index]
                GlassCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.selectProject(project)
                            viewModel.setTab(NavigationTab.PROJECTS)
                        }
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
                                    .size(10.dp)
                                    .clip(CircleShape)
                                    .background(
                                        try {
                                            Color(android.graphics.Color.parseColor(project.colorHex))
                                        } catch (e: Exception) {
                                            NeonPurple
                                        }
                                    )
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = project.title,
                                    color = TextPrimary,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = project.description,
                                    color = TextMuted,
                                    fontSize = 11.sp,
                                    maxLines = 1
                                )
                            }
                        }

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "مشاهده",
                            tint = TextMuted,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}

@Composable
fun StatMiniCard(
    title: String,
    value: String,
    unit: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    GlassCard(
        modifier = modifier,
        backgroundColor = CosmicCardSurface.copy(alpha = 0.85f),
        borderColor = color.copy(alpha = 0.35f)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = value,
                color = color,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = title,
                color = TextSecondary,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1
            )
        }
    }
}

@Composable
fun AiActionCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    accentColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    GlassCard(
        modifier = modifier.clickable(onClick = onClick),
        backgroundColor = CosmicCardSurface.copy(alpha = 0.9f),
        borderColor = accentColor.copy(alpha = 0.3f)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(accentColor.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = accentColor,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = title,
                color = TextPrimary,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subtitle,
                color = TextMuted,
                fontSize = 11.sp,
                maxLines = 1
            )
        }
    }
}
