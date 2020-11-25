package com.github.mrcjkb.jfxfilechooseradapter.impl

import com.github.mrcjkb.jfxfilechooseradapter.adapter.api.IDirectoryChooserAdapter
import com.github.mrcjkb.jfxfilechooseradapter.adapter.api.IJavaFxChooserAdapter
import com.github.mrcjkb.jfxfilechooseradapter.adapter.api.IJavaFxChooserAdapterInternal
import javafx.stage.DirectoryChooser
import java.io.File

class DirectoryChooserAdapter(private val javaFxSwingAdapter: IJavaFxChooserAdapterInternal): IDirectoryChooserAdapter, IJavaFxChooserAdapter by javaFxSwingAdapter {

    private val directoryChooser: DirectoryChooser = DirectoryChooser()

    override fun showDialog(): File? {
        initialDirectory?.takeIf { it.isDirectory }?.let { directoryChooser.initialDirectory = it }
        title.let { directoryChooser.title = it }
        return runPlatformTaskAndBlockEdt({ directoryChooser.showDialog(javaFxSwingAdapter.javaFxParentWindow) }, javaFxSwingAdapter.swingParentWindow)
    }
}