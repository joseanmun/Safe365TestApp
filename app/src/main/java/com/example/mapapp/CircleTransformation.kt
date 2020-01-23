/*
 * Created by Anselmo Jose Munoz Medina on 1/23/20 3:36 PM
 * Email: Joseanmun@gmail.com
 * Copyright (c) 2020. All rights reserved.
 * Last modified 1/23/20 3:36 PM
 *
 */

package com.example.mapapp

import android.graphics.*
import com.squareup.picasso.Transformation

class CircleTransformation : Transformation {
    companion object {
        private val KEY = "circleImageTransformation"
    }

    override fun transform(source: Bitmap): Bitmap {

        val minEdge = Math.min(source.width, source.height)
        val dx = (source.width - minEdge) / 2
        val dy = (source.height - minEdge) / 2

        // Init shader
        val shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        val matrix = Matrix()
        matrix.setTranslate(
            (-dx).toFloat(),
            (-dy).toFloat()
        )   // Move the target area to center of the source bitmap
        shader.setLocalMatrix(matrix)

        // Init paint
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.shader = shader

        // Create and draw circle bitmap
        val output = Bitmap.createBitmap(minEdge, minEdge, source.config)
        val canvas = Canvas(output)
        canvas.drawOval(RectF(0f, 0f, minEdge.toFloat(), minEdge.toFloat()), paint)

        source.recycle()

        return output
    }

    override fun key(): String = KEY
}