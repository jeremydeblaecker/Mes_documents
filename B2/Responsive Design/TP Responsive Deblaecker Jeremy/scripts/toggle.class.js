var toggle = (elem, cl) => {
  const el = document.querySelectorAll(elem);
  el.forEach(e => {
    const c = e.className;
    e.className = c.indexOf(cl) > 0 ? c.replace(cl, '') : `${c} ${cl}`;
  })
}