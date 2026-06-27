package com.trakky.app.data

data class Salon(
    val id: String,
    val name: String,
    val tagline: String,
    val area: String,
    val city: String,
    val distanceKm: Double,
    val rating: Double,
    val priceFrom: Int,
    val image: String,
    val services: List<String>,
    val lat: Double,
    val lng: Double
)

data class Service(
    val id: String, val name: String, val icon: String, val durationMin: Int, val price: Int
)

object SampleData {
    val services = listOf(
        Service("s1","Signature Haircut","✂️", 45, 499),
        Service("s2","Beard Sculpt","🪒", 30, 299),
        Service("s3","Hair Color","🎨", 90, 1299),
        Service("s4","Hair Spa","💆", 60, 899),
        Service("s5","Shave & Glow","🧖", 40, 399),
        Service("s6","Keratin Smoothing","✨", 120, 2499),
        Service("s7","Kids Cut","🧒", 25, 249),
        Service("s8","Bridal Styling","👰", 180, 4999),
    )

    val salons = listOf(
        Salon("1","Velvet Chair Studio","Master barbers · Royal experience","Koregaon Park","Pune",0.8,4.9,399,
            "https://images.unsplash.com/photo-1521590832167-7bcbfaa6381f?w=900",
            listOf("Haircut","Beard","Shave","Hair Spa"), 18.5362, 73.8939),
        Salon("2","Rose Gold Salon & Spa","Hair · Color · Glow","Bandra West","Mumbai",1.4,4.8,599,
            "https://images.unsplash.com/photo-1560066984-138dadb4c035?w=900",
            listOf("Haircut","Color","Spa","Keratin"), 19.0596, 72.8295),
        Salon("3","The Gentleman's Den","Old-school barbershop","Indiranagar","Bangalore",2.1,4.7,349,
            "https://images.unsplash.com/photo-1599351431202-1e0f0137899a?w=900",
            listOf("Haircut","Beard","Shave"), 12.9719, 77.6412),
        Salon("4","Lumière Hair Studio","Premium color specialists","Hauz Khas","Delhi",3.2,4.9,899,
            "https://images.unsplash.com/photo-1582095133179-bfd08e2fc6b3?w=900",
            listOf("Color","Keratin","Spa","Bridal"), 28.5494, 77.2001),
        Salon("5","Blade & Brush","Where craft meets care","Viman Nagar","Pune",4.5,4.6,299,
            "https://images.unsplash.com/photo-1503951914875-452162b0f3f1?w=900",
            listOf("Haircut","Beard","Kids Cut"), 18.5679, 73.9143),
        Salon("6","Ivory Lounge","Bridal & party styling","Jubilee Hills","Hyderabad",5.1,4.8,1299,
            "https://images.unsplash.com/photo-1522337360788-8b13dee7a37e?w=900",
            listOf("Bridal","Color","Spa"), 17.4239, 78.4738),
    )

    val cities = listOf(
        "Pune" to "https://images.unsplash.com/photo-1599351431202-1e0f0137899a?w=600",
        "Mumbai" to "https://images.unsplash.com/photo-1560066984-138dadb4c035?w=600",
        "Bangalore" to "https://images.unsplash.com/photo-1521590832167-7bcbfaa6381f?w=600",
        "Delhi" to "https://images.unsplash.com/photo-1582095133179-bfd08e2fc6b3?w=600",
        "Hyderabad" to "https://images.unsplash.com/photo-1522337360788-8b13dee7a37e?w=600",
    )
}
